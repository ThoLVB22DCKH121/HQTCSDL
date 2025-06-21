package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.converter.ProductionStageDTOConverter;
import org.example.quanlysanxuatxemay.entity.*;
import org.example.quanlysanxuatxemay.enums.ProductionStatus;
import org.example.quanlysanxuatxemay.model.ProductionStageDTO;
import org.example.quanlysanxuatxemay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductionStageService {

    @Autowired
    private MotorbikeRepository motorbikeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductionStageRepository productionStageRepository;

    @Autowired
    private ProductionStageDTOConverter productionStageDTOConverter;

    public ProductionStage createProductionStage(
            String motorbikeId,
            List<String> employeeIds
    ) {
        Motorbike motorbike = motorbikeRepository.findById(motorbikeId)
                .orElseThrow(() -> new RuntimeException("Motorbike not found"));

        List<Employee> employees = employeeRepository.findAllByEmployeeIdIn(employeeIds);

        ProductionStage stage = new ProductionStage();
        stage.setMotorbike(motorbike);
        stage.setEmployees(employees);
        stage.setStatus(ProductionStatus.InProgress);

        return productionStageRepository.save(stage);
    }

    public ProductionStage updateProductionStageStatus(Long stageId, ProductionStatus newStatus) {
        ProductionStage stage = productionStageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("ProductionStage not found"));
        stage.setStatus(newStatus);
        return productionStageRepository.save(stage);
    }

    public Page<ProductionStage> searchByDateRange(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return productionStageRepository.findByDateRange(fromDate, toDate, pageable);
    }

    public Page<Motorbike> searchMotorbikes(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return motorbikeRepository.findAll(pageable);
        }
        return motorbikeRepository.findByMotorbikeNameContainingIgnoreCase(keyword, pageable);
    }
}