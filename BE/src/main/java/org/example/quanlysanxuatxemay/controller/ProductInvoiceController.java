package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.service.ProductInvoiceService;
import org.example.quanlysanxuatxemay.service.ProductInvoiceDetailService;
import org.example.quanlysanxuatxemay.model.ProductRevenueDTO;
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

import java.util.Map;
import java.util.List;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/product-invoices")
public class ProductInvoiceController {

    @Autowired
    private ProductInvoiceService productInvoiceService;

    @Autowired
    private ProductInvoiceDetailService productInvoiceDetailService;

    @GetMapping("/monthly-revenue")
    public ResponseEntity<Map<String, Long>> getMonthlyRevenue(@RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(productInvoiceService.getMonthlyRevenue(year));
    }

    @GetMapping("/top-selling-products")
    public ResponseEntity<List<Object[]>> getTopSellingProductsWithRevenue(@RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(productInvoiceDetailService.getTopSellingProductsWithRevenue(year));
    }

    @GetMapping("/product-revenue")
    public ResponseEntity<List<ProductRevenueDTO>> getProductRevenue(@RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(productInvoiceDetailService.getProductRevenue(year));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductInvoice>> searchInvoices(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productInvoiceService.searchInvoicesByDate(fromDate, toDate, pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductInvoice> createProductInvoice(@RequestBody ProductInvoice invoice) {
        // Tính tổng tiền từ chi tiết hóa đơn
        long total = 0L;
        if (invoice.getDetails() != null) {
            for (var detail : invoice.getDetails()) {
                detail.setProductInvoice(invoice); // gán lại quan hệ
                total += (detail.getQuantity() * detail.getUnitPrice());
            }
        }
        invoice.setTotalAmount(total);
        ProductInvoice saved = productInvoiceService.save(invoice);
        return ResponseEntity.ok(saved);
    }
}
