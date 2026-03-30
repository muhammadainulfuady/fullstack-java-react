package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "log_aktivitas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAktivitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long idLog;

    @NotNull(message = "Admin wajib diisi")
    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @NotBlank(message = "Aktivitas tidak boleh kosong")
    @Column(name = "aktivitas")
    private String aktivitas;
}