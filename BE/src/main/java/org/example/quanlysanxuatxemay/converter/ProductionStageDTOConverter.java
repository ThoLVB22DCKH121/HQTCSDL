package org.example.quanlysanxuatxemay.converter;
import java.util.stream.Collectors;

import org.example.quanlysanxuatxemay.entity.ProductionStage;
import org.example.quanlysanxuatxemay.model.ProductionStageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductionStageDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeDTOConverter employeeDTOConverter;

    public ProductionStageDTO toProductionStageDTO(ProductionStage productionStage) {
        ProductionStageDTO productionStageDTO = modelMapper.map(productionStage, ProductionStageDTO.class);
        productionStageDTO.setMotorbikeName(productionStage.getMotorbike().getMotorbikeName());
        productionStageDTO.setEmployees(
            productionStage.getEmployees().stream()
                .map(employee -> employeeDTOConverter.toEmployeeDTO(employee))
                .collect(Collectors.toList())
        );
        return productionStageDTO;
    }
}