package org.example.quanlysanxuatxemay.model;

public class ProductRevenueDTO {
    private String motorbikeId;
    private String motorbikeName;
    private Long totalRevenue;

    public ProductRevenueDTO(String motorbikeId, String motorbikeName, Long totalRevenue) {
        this.motorbikeId = motorbikeId;
        this.motorbikeName = motorbikeName;
        this.totalRevenue = totalRevenue;
    }

    public String getMotorbikeId() {
        return motorbikeId;
    }

    public String getMotorbikeName() {
        return motorbikeName;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }
}
