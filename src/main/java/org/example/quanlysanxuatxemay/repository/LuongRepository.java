package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.Luong;
import org.example.quanlysanxuatxemay.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuongRepository extends JpaRepository<Luong, Long> {
    List<Luong> findByNhanVien(NhanVien nhanVien);
    List<Luong> findByLuongcobanGreaterThan(Long luongcoban);

    @Query("SELECT AVG(l.luongcoban + l.phucap) FROM Luong l JOIN l.nhanVien nv WHERE nv.boPhan.maBoPhan = :maBoPhan")
    Double calculateAverageSalaryByDepartment(String maBoPhan);
}
