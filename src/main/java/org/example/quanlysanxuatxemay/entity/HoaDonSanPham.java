package org.example.quanlysanxuatxemay.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoadonsanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonSanPham {

    @Id
    @Column(name = "maHoaDonSanPham", length = 20)
    private String maHoaDonSanPham;

    @Column(name = "tongtien", nullable = false)
    private Long tongtien;

    @Column(name = "ngaygiaodich", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngaygiaodich;

    @ManyToOne
    @JoinColumn(name = "maKhachHang", nullable = false)
    private KhachHang khachHang;

    @OneToMany(mappedBy = "hoaDonSanPham", cascade = CascadeType.ALL)
    private List<ChiTietHoaDonSanPham> chiTietHoaDonSanPhams;
}

