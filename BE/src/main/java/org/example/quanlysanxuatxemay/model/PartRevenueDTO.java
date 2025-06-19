package org.example.quanlysanxuatxemay.model;

public class PartRevenueDTO {
    private String partId;
    private String partName;
    private Long totalRevenue;

    public PartRevenueDTO(String partId, String partName, Long totalRevenue) {
        this.partId = partId;
        this.partName = partName;
        this.totalRevenue = totalRevenue;
    }

    public String getPartId() {
        return partId;
    }

    public String getPartName() {
        return partName;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }
}
