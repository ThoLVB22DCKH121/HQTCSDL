package org.example.quanlysanxuatxemay.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import org.example.quanlysanxuatxemay.enums.ProductionStatus;

@Data
public class ProductionStageDTO {
    private Long id;
    private String motorbikeName;
    private ProductionStatus status;
    private LocalDate date;
    private List<EmployeeDTO> employees;
}