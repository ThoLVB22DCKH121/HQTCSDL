package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.ChiTietHoaDonLinhKien;
import org.example.quanlysanxuatxemay.entity.ChiTietHoaDonLinhKienId;
import org.example.quanlysanxuatxemay.entity.HoaDonLinhKien;
import org.example.quanlysanxuatxemay.entity.LinhKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietHoaDonLinhKienRepository extends JpaRepository<ChiTietHoaDonLinhKien, ChiTietHoaDonLinhKienId> {
    List<ChiTietHoaDonLinhKien> findByHoaDonLinhKien(HoaDonLinhKien hoaDonLinhKien);
    List<ChiTietHoaDonLinhKien> findByLinhKien(LinhKien linhKien);

    @Query("SELECT SUM(c.soluong) FROM ChiTietHoaDonLinhKien c WHERE c.linhKien.maLinhKien = :maLinhKien")
    Long sumSoluongByLinhKien(String maLinhKien);
}