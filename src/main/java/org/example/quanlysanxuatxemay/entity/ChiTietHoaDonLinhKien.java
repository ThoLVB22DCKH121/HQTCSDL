package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chitiethoadonlinhkien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonLinhKien {

    @EmbeddedId
    private ChiTietHoaDonLinhKienId id;

    @ManyToOne
    @MapsId("maHoaDonLinhKien")
    @JoinColumn(name = "maHoaDonLinhKien")
    private HoaDonLinhKien hoaDonLinhKien;

    @ManyToOne
    @MapsId("maLinhKien")
    @JoinColumn(name = "maLinhKien")
    private LinhKien linhKien;

    @Column(name = "soluong", nullable = false)
    private Long soluong;
}

