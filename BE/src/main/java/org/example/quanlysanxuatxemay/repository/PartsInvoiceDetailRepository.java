package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.PartsInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PartsInvoiceDetailRepository extends JpaRepository<PartsInvoiceDetail, Long> {
}
