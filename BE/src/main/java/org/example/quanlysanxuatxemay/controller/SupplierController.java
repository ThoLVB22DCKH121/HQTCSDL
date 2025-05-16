package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.Supplier;
import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<Page<Supplier>> getSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(supplierService.getSuppliers(pageable, keyword));
    }

    @GetMapping("/{supplierId}/parts-invoices")
    public ResponseEntity<Page<PartsInvoice>> getPartsInvoicesBySupplier(
            @PathVariable String supplierId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(supplierService.getPartsInvoicesBySupplier(supplierId, pageable));
    }

    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.addSupplier(supplier), HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable String supplierId,
            @RequestBody Supplier supplier
    ) {
        return ResponseEntity.ok(supplierService.updateSupplier(supplierId, supplier));
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String supplierId) {
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
