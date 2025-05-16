package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.repository.PartsInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PartsInvoiceService {
    @Autowired
    private PartsInvoiceRepository partsInvoiceRepository;

    public Page<PartsInvoice> searchInvoicesByDate(String fromDate, String toDate, Pageable pageable) {
        LocalDateTime from = null;
        LocalDateTime to = null;
        if (fromDate != null && !fromDate.isEmpty()) {
            from = LocalDateTime.parse(fromDate);
        }
        if (toDate != null && !toDate.isEmpty()) {
            to = LocalDateTime.parse(toDate);
        }
        return partsInvoiceRepository.findByDateRange(from, to, pageable);
    }

    public PartsInvoice save(PartsInvoice invoice) {
        return partsInvoiceRepository.save(invoice);
    }
}
