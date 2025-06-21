package org.example.quanlysanxuatxemay.model;

import org.example.quanlysanxuatxemay.entity.ProductInvoiceDetail;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ProductInvoiceDTO {
    private Long productInvoiceId;
    private String customerName;
    private LocalDateTime date;
    private Long totalAmount;
    private InvoiceStatus status;
    private List<ProductInvoiceDetail> productInvoiceDetails;

    public Long getProductInvoiceId() {
        return productInvoiceId;
    }
    public void setProductInvoiceId(Long productInvoiceId) {
        this.productInvoiceId = productInvoiceId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
    public InvoiceStatus getStatus() {
        return status;
    }
    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
    public List<ProductInvoiceDetail> getProductInvoiceDetails() {
        return productInvoiceDetails;
    }
    public void setProductInvoiceDetails(List<ProductInvoiceDetail> productInvoiceDetails) {
        this.productInvoiceDetails = productInvoiceDetails;
    }
}
