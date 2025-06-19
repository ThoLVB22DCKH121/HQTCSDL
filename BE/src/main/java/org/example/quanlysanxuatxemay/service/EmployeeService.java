package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.converter.EmployeeDTOConverter;
import org.example.quanlysanxuatxemay.entity.Employee;
import org.example.quanlysanxuatxemay.model.EmployeeDTO;
import org.example.quanlysanxuatxemay.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDTOConverter employeeDTOConverter;

    public Page<EmployeeDTO> getEmployees(Pageable pageable,String keyword) {
        Page<Employee> employees = employeeRepository.getEmployees(pageable,keyword);
        return employeeDTOConverter.toEmployeeDTOPage(employees);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String employeeId, Employee updateEmployee) {
        Employee employee = employeeRepository.findEmployeesByEmployeeId(employeeId);
        if (employee != null) {
            employee.setEmployeeName(updateEmployee.getEmployeeName());
            employee.setPhone(updateEmployee.getPhone());
            employee.setPosition(updateEmployee.getPosition());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteEmployeeByEmployeeId(employeeId);
    }
}
