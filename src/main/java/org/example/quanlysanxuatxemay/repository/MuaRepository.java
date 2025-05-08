package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.KhachHang;
import org.example.quanlysanxuatxemay.entity.Mua;
import org.example.quanlysanxuatxemay.entity.MuaId;
import org.example.quanlysanxuatxemay.entity.XeMay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuaRepository extends JpaRepository<Mua, MuaId> {
    List<Mua> findByKhachHang(KhachHang khachHang);
    List<Mua> findByXeMay(XeMay xeMay);

    @Query("SELECT COUNT(m) FROM Mua m WHERE m.xeMay.loai = :loaiXe")
    Long countPurchasesByType(String loaiXe);
}

