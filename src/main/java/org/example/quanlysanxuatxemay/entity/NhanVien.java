package org.example.quanlysanxuatxemay.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nhanvien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @Column(name = "maNhanVien", length = 20)
    private String maNhanVien;

    @Column(name = "tenNhanVien", nullable = false, length = 255)
    private String tenNhanVien;

    @Column(name = "soBHYT", nullable = false, length = 255)
    private String soBHYT;

    @Column(name = "vitrilamviec", nullable = false, length = 255)
    private String vitrilamviec;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "maBoPhan", nullable = false)
    private BoPhan boPhan;

    @JsonIgnore
    @OneToMany(mappedBy = "quanLy")
    private List<BoPhan> boPhansQuanLy;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<Luong> luongs;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<SanXuatXeMay> sanXuatXeMays;
}
