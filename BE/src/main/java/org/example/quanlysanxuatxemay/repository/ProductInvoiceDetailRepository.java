package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.ProductInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductInvoiceDetailRepository extends JpaRepository<ProductInvoiceDetail, Long> {
    @Query("SELECT d.motorbike.motorbikeId, d.motorbike.motorbikeName, SUM(d.quantity) as totalSold, SUM(d.quantity * d.unitPrice) as totalRevenue " +
           "FROM ProductInvoiceDetail d " +
           "WHERE YEAR(d.productInvoice.date) = :year AND d.productInvoice.status = 'SUCCESS' " +
           "GROUP BY d.motorbike.motorbikeId, d.motorbike.motorbikeName " +
           "ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProductsWithRevenueByYear(@org.springframework.data.repository.query.Param("year") Integer year);

    @Query("SELECT d.motorbike.motorbikeId, d.motorbike.motorbikeName, SUM(d.quantity * d.unitPrice) as totalRevenue " +
           "FROM ProductInvoiceDetail d " +
           "WHERE YEAR(d.productInvoice.date) = :year AND d.productInvoice.status = 'SUCCESS' " +
           "GROUP BY d.motorbike.motorbikeId, d.motorbike.motorbikeName " +
           "ORDER BY totalRevenue DESC")
    List<Object[]> findProductRevenueByYear(@org.springframework.data.repository.query.Param("year") Integer year);
}
