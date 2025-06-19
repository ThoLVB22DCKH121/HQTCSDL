package org.example.quanlysanxuatxemay.repository;
import org.example.quanlysanxuatxemay.entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MotorbikeRepository extends JpaRepository<Motorbike, String> {
    Page<Motorbike> findByMotorbikeNameContainingIgnoreCase(String keyword, Pageable pageable);
}
