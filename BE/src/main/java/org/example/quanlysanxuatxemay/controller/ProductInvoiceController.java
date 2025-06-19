package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.service.ProductInvoiceService;
import org.example.quanlysanxuatxemay.entity.ProductInvoice;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.PageRequest;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

@RestController
@RequestMapping("/api/product-invoices")
public class ProductInvoiceController {

    @Autowired
    private ProductInvoiceService productInvoiceService;

    @GetMapping
    public ResponseEntity<Page<ProductInvoice>> searchInvoices(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductInvoice> pageResult = productInvoiceService.searchInvoicesByDate(fromDate, toDate, pageable);
        pageResult.forEach(inv -> {
            if (inv.getCustomer() != null) {
                inv.getCustomer().setProductInvoiceList(null);
            }
        });
        return ResponseEntity.ok(productInvoiceService.searchInvoicesByDate(fromDate, toDate, pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductInvoice> createProductInvoice(@RequestBody ProductInvoice invoice) {
        long total = 0L;
        if (invoice.getDetails() != null) {
            for (var detail : invoice.getDetails()) {
                detail.setProductInvoice(invoice);
                total += (detail.getQuantity() * detail.getUnitPrice());
            }
        }
        invoice.setTotalAmount(total);
        ProductInvoice saved = productInvoiceService.save(invoice);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{productInvoiceId}/status")
    public ResponseEntity<ProductInvoice> updateProductInvoiceStatus(
            @PathVariable String productInvoiceId,
            @RequestParam InvoiceStatus status) {
        ProductInvoice invoice = productInvoiceService.findById(productInvoiceId);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        invoice.setStatus(status);
        ProductInvoice updated = productInvoiceService.save(invoice);
        return ResponseEntity.ok(updated);
    }
}
