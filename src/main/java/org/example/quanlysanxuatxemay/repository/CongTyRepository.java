package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.CongTy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongTyRepository extends JpaRepository<CongTy, String> {
    List<CongTy> findByTenCongTyContaining(String tenCongTy);
    boolean existsByMaCongTy(String maCongTy);
}
