package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "xemay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XeMay {

    @Id
    @Column(name = "maXeMay", length = 20)
    private String maXeMay;

    @Column(name = "tenXeMay", nullable = false, length = 255)
    private String tenXeMay;

    @Column(name = "giaXeMay", nullable = false)
    private Long giaXeMay;

    @Column(name = "dungtich", nullable = false)
    private Long dungtich;

    @Column(name = "loai", nullable = false, length = 255)
    private String loai;

    @OneToMany(mappedBy = "xeMay", cascade = CascadeType.ALL)
    private List<ChiTietHoaDonSanPham> chiTietHoaDonSanPhams;

    @OneToMany(mappedBy = "xeMay", cascade = CascadeType.ALL)
    private List<Mua> muas;

    @OneToMany(mappedBy = "xeMay", cascade = CascadeType.ALL)
    private List<SanXuatXeMay> sanXuatXeMays;
}