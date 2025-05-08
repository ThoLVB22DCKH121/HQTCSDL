package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sodienthoai")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoDienThoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SoDienThoai", nullable = false, length = 255)
    private String soDienThoai;

    @ManyToOne
    @JoinColumn(name = "maCongTy", nullable = false)
    private CongTy congTy;
}
