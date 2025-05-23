package org.example.quanlysanxuatxemay.enums;

public enum ProductionStatus {
    IN_PROGRESS("Đang xử lý"),
    COMPLETED("Hoàn thành");

    private final String displayName;

    ProductionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
