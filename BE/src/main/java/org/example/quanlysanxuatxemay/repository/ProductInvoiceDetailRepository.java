package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.ProductInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductInvoiceDetailRepository extends JpaRepository<ProductInvoiceDetail, Long> {
}
