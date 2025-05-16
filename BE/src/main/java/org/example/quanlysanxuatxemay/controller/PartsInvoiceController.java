package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.service.PartsInvoiceService;
import org.example.quanlysanxuatxemay.service.PartsInvoiceDetailService;
import org.example.quanlysanxuatxemay.model.PartImportStatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/parts-invoices")
public class PartsInvoiceController {
    @Autowired
    private PartsInvoiceService partsInvoiceService;

    @Autowired
    private PartsInvoiceDetailService partsInvoiceDetailService;

    @GetMapping("/search")
    public ResponseEntity<Page<PartsInvoice>> searchInvoices(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partsInvoiceService.searchInvoicesByDate(fromDate, toDate, pageable));
    }

    @GetMapping("/part-import-stats")
    public ResponseEntity<List<PartImportStatDTO>> getPartImportStats() {
        return ResponseEntity.ok(partsInvoiceDetailService.getPartImportStats());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PartsInvoice> createPartsInvoice(@RequestBody PartsInvoice invoice) {
        // Tính tổng tiền từ chi tiết hóa đơn nhập linh kiện
        long total = 0L;
        if (invoice.getDetails() != null) {
            for (var detail : invoice.getDetails()) {
                detail.setPartsInvoice(invoice); // gán lại quan hệ
                total += (detail.getQuantity() * detail.getUnitPrice());
            }
        }
        invoice.setTotalAmount(total);
        PartsInvoice saved = partsInvoiceService.save(invoice);
        return ResponseEntity.ok(saved);
    }
}
