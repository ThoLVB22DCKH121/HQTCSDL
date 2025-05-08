package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.CongTy;
import org.example.quanlysanxuatxemay.entity.HoaDonLinhKien;
import org.example.quanlysanxuatxemay.entity.NhaCungCapLinhKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonLinhKienRepository extends JpaRepository<HoaDonLinhKien, String> {
    List<HoaDonLinhKien> findByNgaygiaodichBetween(Date startDate, Date endDate);
    List<HoaDonLinhKien> findByCongTy(CongTy congTy);
    List<HoaDonLinhKien> findByNhaCungCap(NhaCungCapLinhKien nhaCungCap);
    List<HoaDonLinhKien> findByTongtienGreaterThan(Long tongtien);

    @Query("SELECT SUM(h.tongtien) FROM HoaDonLinhKien h WHERE h.ngaygiaodich BETWEEN :startDate AND :endDate")
    Long sumTongtienByNgaygiaodichBetween(Date startDate, Date endDate);
}

