package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "congty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CongTy {

    @Id
    @Column(name = "maCongTy", length = 20)
    private String maCongTy;

    @Column(name = "tenCongTy", nullable = false, length = 255)
    private String tenCongTy;

    @Column(name = "diachiCongTy", nullable = false, length = 255)
    private String diachiCongTy;

    @OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL)
    private List<BoPhan> boPhans;

    @OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL)
    private List<HoaDonLinhKien> hoaDonLinhKiens;

    @OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL)
    private List<SoDienThoai> soDienThoais;
}