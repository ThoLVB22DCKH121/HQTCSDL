package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bophan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoPhan {

    @Id
    @Column(name = "maBoPhan", length = 20)
    private String maBoPhan;

    @Column(name = "tenBoPhan", nullable = false, length = 255)
    private String tenBoPhan;

    @ManyToOne
    @JoinColumn(name = "maQuanLy", nullable = false)
    private NhanVien quanLy;

    @ManyToOne
    @JoinColumn(name = "maCongTy", nullable = false)
    private CongTy congTy;

    @OneToMany(mappedBy = "boPhan", cascade = CascadeType.ALL)
    private List<NhanVien> nhanViens;
}