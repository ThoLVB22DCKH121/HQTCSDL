package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chitiethoadonsanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonSanPham {

    @EmbeddedId
    private ChiTietHoaDonSanPhamId id;

    @ManyToOne
    @MapsId("maXeMay")
    @JoinColumn(name = "maXeMay")
    private XeMay xeMay;

    @ManyToOne
    @MapsId("maHoaDonSanPham")
    @JoinColumn(name = "maHoaDonSanPham")
    private HoaDonSanPham hoaDonSanPham;

    @Column(name = "soluong", nullable = false)
    private Long soluong;
}