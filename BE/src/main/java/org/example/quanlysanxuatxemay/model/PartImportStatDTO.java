package org.example.quanlysanxuatxemay.model;

public class PartImportStatDTO {
    private String partId;
    private String partName;
    private Long totalQuantity;
    private Long totalCost;

    public PartImportStatDTO(String partId, String partName, Long totalQuantity, Long totalCost) {
        this.partId = partId;
        this.partName = partName;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    public String getPartId() { return partId; }
    public String getPartName() { return partName; }
    public Long getTotalQuantity() { return totalQuantity; }
    public Long getTotalCost() { return totalCost; }
}
