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
public class SanXuatXeMayId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String maNhanVien;

    private String maLinhKien;

    private String maXeMay;
}
