package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
    @Query("SELECT s FROM Supplier s WHERE :keyword IS NULL OR LOWER(s.supplierName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Supplier> findSuppliers(
            Pageable pageable,
            @Param("keyword") String keyword
    );
}
