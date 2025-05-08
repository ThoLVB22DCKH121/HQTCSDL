package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.LinhKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinhKienRepository extends JpaRepository<LinhKien, String> {
    List<LinhKien> findByTenLinhKienContaining(String tenLinhKien);
    List<LinhKien> findByGiaLinhKienBetween(Long min, Long max);

    @Query("SELECT lk FROM LinhKien lk JOIN lk.chiTietHoaDonLinhKiens cthdlk " +
            "GROUP BY lk.maLinhKien ORDER BY SUM(cthdlk.soluong) DESC")
    List<LinhKien> findMostPurchasedLinhKien();
}
