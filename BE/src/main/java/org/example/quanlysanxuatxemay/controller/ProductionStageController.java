package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.ProductionStage;
import org.example.quanlysanxuatxemay.enums.ProductionStatus;
import org.example.quanlysanxuatxemay.service.ProductionStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.example.quanlysanxuatxemay.model.ProductionStageDTO;

@RestController
@RequestMapping("/api/production-stages")
public class ProductionStageController {

    @Autowired
    private ProductionStageService productionStageService;

    // Tạo mới quá trình sản xuất
    @PostMapping
    public ProductionStage createProductionStage(
            @RequestParam String motorbikeId,
            @RequestBody List<String> employeeIds
    ) {
        return productionStageService.createProductionStage(motorbikeId, employeeIds);
    }

    // Sửa trạng thái quá trình sản xuất
    @PutMapping("/{stageId}/status")
    public ProductionStage updateProductionStageStatus(
            @PathVariable Long stageId,
            @RequestParam ProductionStatus status
    ) {
        return productionStageService.updateProductionStageStatus(stageId, status);
    }

    @GetMapping("/search")
    public Page<ProductionStageDTO> searchByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productionStageService.searchByDateRange(startDate, endDate, pageable);
    }
}