package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.BoPhan;
import org.example.quanlysanxuatxemay.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    List<NhanVien> findByBoPhan(BoPhan boPhan);
    List<NhanVien> findByVitrilamviec(String vitrilamviec);

    @Query("SELECT nv FROM NhanVien nv JOIN nv.luongs l WHERE l.luongcoban > :luongCoban")
    List<NhanVien> findByLuongCobanGreaterThan(Long luongCoban);
}
