package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mua")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mua {

    @EmbeddedId
    private MuaId id;

    @ManyToOne
    @MapsId("maXeMay")
    @JoinColumn(name = "maXeMay")
    private XeMay xeMay;

    @ManyToOne
    @MapsId("maKhachHang")
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;
}
