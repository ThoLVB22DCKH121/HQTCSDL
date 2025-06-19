package org.example.quanlysanxuatxemay.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.example.quanlysanxuatxemay.entity.ProductionStage;
public interface ProductionStageRepository extends JpaRepository<ProductionStage, Long> {
    Page<ProductionStage> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
