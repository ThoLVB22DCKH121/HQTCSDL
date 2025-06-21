package org.example.quanlysanxuatxemay.enums;

public enum InvoiceStatus {
    pending("Chờ xử lý"),
    completed("Thành công"),
    cancelled("Hủy");

    private final String displayName;

    InvoiceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
