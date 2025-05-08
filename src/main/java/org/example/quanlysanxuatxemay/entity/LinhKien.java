package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "linhkien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinhKien {

    @Id
    @Column(name = "maLinhKien", length = 20)
    private String maLinhKien;

    @Column(name = "giaLinhKien", nullable = false)
    private Long giaLinhKien;

    @Column(name = "tenLinhKien", nullable = false, length = 255)
    private String tenLinhKien;

    @OneToMany(mappedBy = "linhKien", cascade = CascadeType.ALL)
    private List<ChiTietHoaDonLinhKien> chiTietHoaDonLinhKiens;

    @OneToMany(mappedBy = "linhKien", cascade = CascadeType.ALL)
    private List<SanXuatXeMay> sanXuatXeMays;
}
