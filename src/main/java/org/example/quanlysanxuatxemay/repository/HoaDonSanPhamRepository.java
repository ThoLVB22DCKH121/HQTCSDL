package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.HoaDonSanPham;
import org.example.quanlysanxuatxemay.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonSanPhamRepository extends JpaRepository<HoaDonSanPham, String> {
    List<HoaDonSanPham> findByNgaygiaodichBetween(Date startDate, Date endDate);
    List<HoaDonSanPham> findByKhachHang(KhachHang khachHang);
    List<HoaDonSanPham> findByTongtienGreaterThan(Long tongtien);

    @Query("SELECT SUM(h.tongtien) FROM HoaDonSanPham h WHERE h.ngaygiaodich BETWEEN :startDate AND :endDate")
    Long calculateRevenueByDateRange(Date startDate, Date endDate);
}

