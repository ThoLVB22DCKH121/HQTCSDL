package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.converter.ProductionStageDTOConverter;
import org.example.quanlysanxuatxemay.entity.ProductionStage;
import org.example.quanlysanxuatxemay.enums.ProductionStatus;
import org.example.quanlysanxuatxemay.repository.ProductionStageRepository;
import org.example.quanlysanxuatxemay.service.ProductionStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private ProductionStageDTOConverter productionStageDTOConverter;

    @PutMapping("/{stageId}")
    public ProductionStage updateProductionStageStatus(
            @PathVariable Long stageId,
            @RequestParam ProductionStatus status
    ) {
        return productionStageService.updateProductionStageStatus(stageId, status);
    }

    @GetMapping
    public ResponseEntity<Page<ProductionStageDTO>> searchByDate(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productionStageService
                .searchByDateRange(startDate, endDate, pageable)
                .map(productionStage -> productionStageDTOConverter.toProductionStageDTO(productionStage)));
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