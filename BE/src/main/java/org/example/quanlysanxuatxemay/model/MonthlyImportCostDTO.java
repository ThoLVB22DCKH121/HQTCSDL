package org.example.quanlysanxuatxemay.model;

public class MonthlyImportCostDTO {
    private int month;
    private long totalCost;

    public MonthlyImportCostDTO(int month, long totalCost) {
        this.month = month;
        this.totalCost = totalCost;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }
}
