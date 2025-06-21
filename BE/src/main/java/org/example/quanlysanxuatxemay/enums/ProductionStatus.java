package org.example.quanlysanxuatxemay.enums;

public enum ProductionStatus {
    Planned("Đã lên kế hoạch"),
    InProgress("Đang thực hiện"),
    Completed("Đã hoàn thành"),
    OnHold("Tạm dừng"),
    Delayed("Bị trì hoãn");

    private final String displayName;

    ProductionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}