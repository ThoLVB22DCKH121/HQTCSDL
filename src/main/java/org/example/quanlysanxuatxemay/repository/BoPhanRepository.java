package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.BoPhan;
import org.example.quanlysanxuatxemay.entity.CongTy;
import org.example.quanlysanxuatxemay.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoPhanRepository extends JpaRepository<BoPhan, String> {
    List<BoPhan> findByTenBoPhanContaining(String tenBoPhan);
    List<BoPhan> findByCongTy(CongTy congTy);
    List<BoPhan> findByQuanLy(NhanVien quanLy);
}