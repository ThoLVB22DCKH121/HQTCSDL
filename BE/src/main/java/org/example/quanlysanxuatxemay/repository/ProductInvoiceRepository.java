package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.ProductInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;

public interface ProductInvoiceRepository extends JpaRepository<ProductInvoice, String> {

    Page<ProductInvoice> findByCustomer_CustomerId(String customerId, Pageable pageable);

    @Query("SELECT p FROM ProductInvoice p WHERE (:fromDate IS NULL OR p.date >= :fromDate) AND (:toDate IS NULL OR p.date <= :toDate)")
    Page<ProductInvoice> findByDateRange(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate, Pageable pageable);
}
