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
import org.example.quanlysanxuatxemay.entity.Motorbike;
import org.example.quanlysanxuatxemay.repository.MotorbikeRepository;

@RestController
@RequestMapping("/api/production-stages")
public class ProductionStageController {

    @Autowired
    private ProductionStageService productionStageService;


    @PostMapping
    public ProductionStage createProductionStage(
            @RequestParam String motorbikeId,
            @RequestBody List<String> employeeIds
    ) {
        return productionStageService.createProductionStage(motorbikeId, employeeIds);
    }

    @PutMapping("/{stageId}/status")
    public ProductionStage updateProductionStageStatus(
            @PathVariable Long stageId,
            @RequestParam ProductionStatus status
    ) {
        return productionStageService.updateProductionStageStatus(stageId, status);
    }

    @GetMapping
    public Page<ProductionStageDTO> searchByDate(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productionStageService.searchByDateRange(startDate, endDate, pageable);
    }

    @GetMapping("/motorbikes")
    public Page<Motorbike> getMotorbikes(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productionStageService.searchMotorbikes(keyword, pageable);
    }
}