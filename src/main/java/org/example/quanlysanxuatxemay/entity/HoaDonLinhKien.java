package org.example.quanlysanxuatxemay.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoadonlinhkien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonLinhKien {

    @Id
    @Column(name = "maHoaDonLinhKien", length = 20)
    private String maHoaDonLinhKien;

    @Column(name = "tongtien", nullable = false)
    private Long tongtien;

    @Column(name = "ngaygiaodich", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngaygiaodich;

    @ManyToOne
    @JoinColumn(name = "maNhaCungCap", nullable = false)
    private NhaCungCapLinhKien nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "maCongTy", nullable = false)
    private CongTy congTy;

    @OneToMany(mappedBy = "hoaDonLinhKien", cascade = CascadeType.ALL)
    private List<ChiTietHoaDonLinhKien> chiTietHoaDonLinhKiens;
}