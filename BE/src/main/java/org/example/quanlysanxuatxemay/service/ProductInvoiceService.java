package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.enums.InvoiceStatus;
import org.example.quanlysanxuatxemay.repository.ProductInvoiceRepository;
import org.example.quanlysanxuatxemay.entity.ProductInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public ProductInvoice updateStatus(String id, InvoiceStatus status) {
        ProductInvoice invoice = productInvoiceRepository.findById(id).orElse(null);
        if (invoice == null) return null;
        invoice.setStatus(status);
        return productInvoiceRepository.save(invoice);
    }
}
