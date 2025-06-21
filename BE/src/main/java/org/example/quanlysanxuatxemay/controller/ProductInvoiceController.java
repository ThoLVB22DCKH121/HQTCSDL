package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.converter.ProductInvoiceDTOConverter;
import org.example.quanlysanxuatxemay.model.ProductInvoiceDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.PageRequest;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

@RestController
@RequestMapping("/api/product-invoices")
public class ProductInvoiceController {

    @Autowired
    private ProductInvoiceService productInvoiceService;
    @Autowired
    private ProductInvoiceDTOConverter productInvoiceDTOConverter;

    @GetMapping
    public ResponseEntity<Page<ProductInvoiceDTO>> searchInvoices(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductInvoiceDTO> pageResult = productInvoiceService
                .searchInvoicesByDate(fromDate, toDate, pageable)
                .map(productInvoice -> productInvoiceDTOConverter.toDTO(productInvoice));
        return ResponseEntity.ok(pageResult);
    }

    @PutMapping("/{productInvoiceId}")
    public ResponseEntity<ProductInvoice> updateProductInvoiceStatus(
            @PathVariable String productInvoiceId,
            @RequestParam String status) {
        InvoiceStatus enumStatus;
        try {
            enumStatus = InvoiceStatus.valueOf(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        ProductInvoice updated = productInvoiceService.updateStatus(productInvoiceId, enumStatus);
        return ResponseEntity.ok(updated);
    }
}
