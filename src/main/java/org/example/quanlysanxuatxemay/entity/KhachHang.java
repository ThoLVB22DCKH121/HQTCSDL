package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "khachhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {

    @Id
    @Column(name = "maKhachHang", length = 20)
    private String maKhachHang;

    @Column(name = "tenKhachHang", nullable = false, length = 255)
    private String tenKhachHang;

    @Column(name = "diachi", nullable = false, length = 255)
    private String diachi;

    @Column(name = "sodienthoai", nullable = false, length = 20)
    private String sodienthoai;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<HoaDonSanPham> hoaDonSanPhams;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<Mua> muas;
}