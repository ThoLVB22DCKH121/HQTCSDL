package org.example.quanlysanxuatxemay.model;

import org.example.quanlysanxuatxemay.entity.PartsInvoiceDetail;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PartsInvoiceDTO {
    private Long partsInvoiceId;
    private LocalDateTime date;
    private Long totalAmount;
    private InvoiceStatus status;
    private String supplierName;
    private List<PartsInvoiceDetail> partsInvoiceDetails;

    public Long getPartsInvoiceId() {
        return partsInvoiceId;
    }
    public void setPartsInvoiceId(Long partsInvoiceId) {
        this.partsInvoiceId = partsInvoiceId;
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
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public List<PartsInvoiceDetail> getPartsInvoiceDetails() {
        return partsInvoiceDetails;
    }
    public void setPartsInvoiceDetails(List<PartsInvoiceDetail> partsInvoiceDetails) {
        this.partsInvoiceDetails = partsInvoiceDetails;
    }
}
