package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.XeMay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XeMayRepository extends JpaRepository<XeMay, String> {
    List<XeMay> findByTenXeMayContaining(String tenXeMay);
    List<XeMay> findByGiaXeMayBetween(Long min, Long max);
    List<XeMay> findByDungtich(Long dungtich);
    List<XeMay> findByLoai(String loai);

    @Query("SELECT x FROM XeMay x JOIN x.chiTietHoaDonSanPhams c " +
            "GROUP BY x.maXeMay ORDER BY SUM(c.soluong) DESC")
    List<XeMay> findTopSellingMotorcycles();
}

