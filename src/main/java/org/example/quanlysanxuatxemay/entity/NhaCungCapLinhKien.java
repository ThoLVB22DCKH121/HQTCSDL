package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nhacungcaplinhkien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCapLinhKien {

    @Id
    @Column(name = "maNhaCungCap", length = 20)
    private String maNhaCungCap;

    @Column(name = "tenNhaCungCap", nullable = false, length = 20)
    private String tenNhaCungCap;

    @OneToMany(mappedBy = "nhaCungCap", cascade = CascadeType.ALL)
    private List<HoaDonLinhKien> hoaDonLinhKiens;
}