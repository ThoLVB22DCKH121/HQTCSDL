package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.entity.Supplier;
import org.example.quanlysanxuatxemay.repository.SupplierRepository;
import org.example.quanlysanxuatxemay.repository.PartsInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PartsInvoiceRepository partsInvoiceRepository;

    public Page<Supplier> getSuppliers(Pageable pageable, String keyword) {
        return supplierRepository.findSuppliers(pageable, keyword);
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(String supplierId, Supplier update) {
        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
        if (supplier == null) throw new IllegalArgumentException("Supplier not found");
        supplier.setSupplierName(update.getSupplierName());
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(String supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    public Page<PartsInvoice> getPartsInvoicesBySupplier(String supplierId, Pageable pageable) {
        return partsInvoiceRepository.findBySupplier_SupplierId(supplierId, pageable);
    }
}
