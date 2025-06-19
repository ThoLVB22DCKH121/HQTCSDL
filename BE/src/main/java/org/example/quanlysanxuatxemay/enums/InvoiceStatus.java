package org.example.quanlysanxuatxemay.enums;

public enum InvoiceStatus {
    PENDING("Chờ xử lý"),
    SUCCESS("Thành công"),
    FAILED("Thất bại"),
    CANCELLED("Hủy");

    private final String displayName;

    InvoiceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
