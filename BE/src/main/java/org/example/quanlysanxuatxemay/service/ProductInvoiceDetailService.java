package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.repository.ProductInvoiceDetailRepository;
import org.example.quanlysanxuatxemay.model.ProductRevenueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductInvoiceDetailService {
    @Autowired
    private ProductInvoiceDetailRepository productInvoiceDetailRepository;


    public List<Object[]> getTopSellingProductsWithRevenue(Integer year) {
        return productInvoiceDetailRepository.findTopSellingProductsWithRevenueByYear(year);
    }

    public List<ProductRevenueDTO> getProductRevenue(Integer year) {
        List<Object[]> results;
        results = productInvoiceDetailRepository.findProductRevenueByYear(year);
        List<ProductRevenueDTO> dtos = new java.util.ArrayList<>();
        for (Object[] row : results) {
            String id = (String) row[0];
            String name = (String) row[1];
            Long revenue = (Long) row[2];
            dtos.add(new ProductRevenueDTO(id, name, revenue));
        }
        return dtos;
    }
}
