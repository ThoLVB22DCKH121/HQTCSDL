package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PartsInvoiceRepository extends JpaRepository<PartsInvoice, String> {
    @Query("SELECT p FROM PartsInvoice p WHERE (:fromDate IS NULL OR p.date >= :fromDate) AND (:toDate IS NULL OR p.date <= :toDate)")
    Page<PartsInvoice> findByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, Pageable pageable);

    Page<PartsInvoice> findBySupplier_SupplierId(String supplierId, Pageable pageable);
}
