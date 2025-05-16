package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.PartsInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PartsInvoiceDetailRepository extends JpaRepository<PartsInvoiceDetail, Long> {
    @Query("SELECT d.part.partId, d.part.partName, SUM(d.quantity) as totalQuantity, SUM(d.quantity * d.unitPrice) as totalCost " +
           "FROM PartsInvoiceDetail d " +
           "WHERE d.partsInvoice.status = 'SUCCESS' " +
           "GROUP BY d.part.partId, d.part.partName " +
           "ORDER BY totalQuantity DESC")
    List<Object[]> findPartImportStats();
}
