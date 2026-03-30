package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 1. Ambil token dari header Postman/Frontend
            String jwt = getJwtFromRequest(request);

            // 2. Jika token ada dan valid
            if (StringUtils.hasText(jwt) && jwtUtils.validateToken(jwt)) {
                // Ambil email dari token
                String email = jwtUtils.getEmailFromToken(jwt);

                // Load data user dari database
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // Buatkan surat izin masuk (Authentication)
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Simpan surat izin tersebut di Security Context (Pintu dibukakan!)
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            System.out.println("Gagal melakukan autentikasi user: " + ex.getMessage());
        }

        // Lanjutkan request ke controller tujuan
        filterChain.doFilter(request, response);
    }

    // Method pembantu untuk mengambil tulisan token yang biasanya diawali dengan
    // "Bearer "
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}