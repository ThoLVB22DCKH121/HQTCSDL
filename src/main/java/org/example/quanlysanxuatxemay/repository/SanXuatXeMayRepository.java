package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanXuatXeMayRepository extends JpaRepository<SanXuatXeMay, SanXuatXeMayId> {
    List<SanXuatXeMay> findByNhanVien(NhanVien nhanVien);
    List<SanXuatXeMay> findByLinhKien(LinhKien linhKien);
    List<SanXuatXeMay> findByXeMay(XeMay xeMay);

    @Query("SELECT SUM(s.soluong) FROM SanXuatXeMay s WHERE s.nhanVien.maNhanVien = :maNhanVien GROUP BY s.nhanVien.maNhanVien")
    Long countTotalProducedByEmployee(String maNhanVien);

    @Query("SELECT s.nhanVien, SUM(s.soluong) as totalProduced FROM SanXuatXeMay s GROUP BY s.nhanVien ORDER BY totalProduced DESC")
    List<Object[]> findTopProducers();
}
