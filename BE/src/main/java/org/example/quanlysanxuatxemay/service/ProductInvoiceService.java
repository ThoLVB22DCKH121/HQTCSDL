package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.repository.ProductInvoiceRepository;
import org.example.quanlysanxuatxemay.entity.ProductInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductInvoiceService {

    @Autowired
    private ProductInvoiceRepository productInvoiceRepository;

    public Page<ProductInvoice> searchInvoicesByDate(String fromDate, String toDate, Pageable pageable) {
        LocalDateTime from = null;
        LocalDateTime to = null;
        if (fromDate != null && !fromDate.isEmpty()) {
            from = LocalDateTime.parse(fromDate);
        }
        if (toDate != null && !toDate.isEmpty()) {
            to = LocalDateTime.parse(toDate);
        }
        return productInvoiceRepository.findByDateRange(from, to, pageable);
    }

    public ProductInvoice save(ProductInvoice invoice) {
        return productInvoiceRepository.save(invoice);
    }

    public ProductInvoice findById(String productInvoiceId) {
        return productInvoiceRepository.findById(productInvoiceId).orElse(null);
    }
}
