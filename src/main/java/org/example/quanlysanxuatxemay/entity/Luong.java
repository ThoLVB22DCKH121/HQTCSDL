package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "luong")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Luong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngaycong", nullable = false)
    private Long ngaycong;

    @Column(name = "luongcoban", nullable = false)
    private Long luongcoban;

    @Column(name = "phucap", nullable = false)
    private Long phucap;

    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien nhanVien;
}
