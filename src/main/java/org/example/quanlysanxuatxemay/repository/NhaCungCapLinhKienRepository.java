package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.NhaCungCapLinhKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaCungCapLinhKienRepository extends JpaRepository<NhaCungCapLinhKien, String> {
    List<NhaCungCapLinhKien> findByTenNhaCungCapContaining(String tenNhaCungCap);
}