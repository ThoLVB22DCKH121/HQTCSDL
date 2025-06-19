package org.example.quanlysanxuatxemay.converter;

import org.example.quanlysanxuatxemay.entity.Employee;
import org.example.quanlysanxuatxemay.model.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    public Page<EmployeeDTO> toEmployeeDTOPage(Page<Employee> employees) {
        return employees.map(employee -> toEmployeeDTO(employee));
    }
}
