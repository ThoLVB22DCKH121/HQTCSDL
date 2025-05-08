package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.ChiTietHoaDonSanPham;
import org.example.quanlysanxuatxemay.entity.ChiTietHoaDonSanPhamId;
import org.example.quanlysanxuatxemay.entity.HoaDonSanPham;
import org.example.quanlysanxuatxemay.entity.XeMay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietHoaDonSanPhamRepository extends JpaRepository<ChiTietHoaDonSanPham, ChiTietHoaDonSanPhamId> {
    List<ChiTietHoaDonSanPham> findByHoaDonSanPham(HoaDonSanPham hoaDonSanPham);
    List<ChiTietHoaDonSanPham> findByXeMay(XeMay xeMay);

    @Query("SELECT SUM(c.soluong) FROM ChiTietHoaDonSanPham c WHERE c.xeMay.maXeMay = :maXeMay")
    Long countTotalSoldByXeMay(String maXeMay);
}
