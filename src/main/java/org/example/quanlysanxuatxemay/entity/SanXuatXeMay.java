package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sanxuatxemay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanXuatXeMay {

    @EmbeddedId
    private SanXuatXeMayId id;

    @ManyToOne
    @MapsId("maNhanVien")
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @MapsId("maLinhKien")
    @JoinColumn(name = "maLinhKien")
    private LinhKien linhKien;

    @ManyToOne
    @MapsId("maXeMay")
    @JoinColumn(name = "maXeMay")
    private XeMay xeMay;

    @Column(name = "soluong", nullable = false)
    private Long soluong;
}