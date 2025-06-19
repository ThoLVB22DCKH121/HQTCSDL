package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE :keyword IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Customer> findCustomers(
            Pageable pageable,
            @Param("keyword") String keyword
    );
}
