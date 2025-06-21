package org.example.quanlysanxuatxemay.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.example.quanlysanxuatxemay.entity.ProductionStage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductionStageRepository extends JpaRepository<ProductionStage, Long> {
    @Query("SELECT p FROM ProductionStage p WHERE (:fromDate IS NULL OR p.date >= :fromDate) AND (:toDate IS NULL OR p.date <= :toDate)")
    Page<ProductionStage> findByDateRange(@Param("fromDate") LocalDate startDate,  @Param("toDate") LocalDate endDate, Pageable pageable);
}
