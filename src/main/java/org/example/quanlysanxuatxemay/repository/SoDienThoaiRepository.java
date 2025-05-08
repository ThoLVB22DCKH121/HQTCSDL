package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.CongTy;
import org.example.quanlysanxuatxemay.entity.SoDienThoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoDienThoaiRepository extends JpaRepository<SoDienThoai, Long> {
    List<SoDienThoai> findByCongTy(CongTy congTy);
    List<SoDienThoai> findBySoDienThoai(String soDienThoai);
}
