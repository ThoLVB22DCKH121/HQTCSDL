package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.service.PartsInvoiceService;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/parts-invoices")
public class PartsInvoiceController {
    @Autowired
    private PartsInvoiceService partsInvoiceService;



    @GetMapping
    public ResponseEntity<Page<PartsInvoice>> searchInvoices(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PartsInvoice> pageResult = partsInvoiceService.searchInvoicesByDate(fromDate, toDate, pageable);
        pageResult.forEach(inv -> {
            if (inv.getSupplier() != null) {
                inv.getSupplier().setPartsInvoiceList(null);
            }
        });
        return ResponseEntity.ok(pageResult);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<PartsInvoice> updateStatus(@PathVariable("id") String id, @RequestParam String status) {
        InvoiceStatus enumStatus;
        try {
            enumStatus = InvoiceStatus.valueOf(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        PartsInvoice saved = partsInvoiceService.updateStatus(id, enumStatus);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }
}
