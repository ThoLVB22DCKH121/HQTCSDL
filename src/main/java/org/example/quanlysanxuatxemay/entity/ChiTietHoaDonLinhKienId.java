package org.example.quanlysanxuatxemay.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDonLinhKienId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "maHoaDonLinhKien", length = 20)
    private String maHoaDonLinhKien;

    @Column(name = "maLinhKien", length = 20)
    private String maLinhKien;
}
