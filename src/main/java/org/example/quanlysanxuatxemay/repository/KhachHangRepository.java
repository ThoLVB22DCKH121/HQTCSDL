package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    List<KhachHang> findByTenKhachHangContaining(String tenKhachHang);
    List<KhachHang> findBySodienthoai(String sodienthoai);
    List<KhachHang> findByDiachiContaining(String diachi);

    @Query("SELECT kh FROM KhachHang kh JOIN kh.hoaDonSanPhams hdsp " +
            "GROUP BY kh.maKhachHang ORDER BY SUM(hdsp.tongtien) DESC")
    List<KhachHang> findTopCustomersByTotalSpend();
}

