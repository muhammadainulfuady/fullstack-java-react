package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    // Menentukan lokasi folder penyimpanan
    private final String UPLOAD_DIR = "uploads/";

    @PostMapping("/gambar")
    public ApiResponse<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Cek apakah file kosong
            if (file.isEmpty()) {
                return new ApiResponse<>(400, "Pilih file terlebih dahulu", null);
            }

            // Buat folder uploads jika belum ada
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate nama file acak agar tidak ada yang bentrok/sama
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String randomFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Simpan file ke folder lokal
            Path filePath = uploadPath.resolve(randomFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Buat URL yang bisa diakses
            String fileUrl = "http://localhost:8080/uploads/" + randomFileName;

            return new ApiResponse<>(200, "Berhasil upload gambar", fileUrl);

        } catch (IOException e) {
            return new ApiResponse<>(500, "Gagal mengunggah file: " + e.getMessage(), null);
        }
    }
}