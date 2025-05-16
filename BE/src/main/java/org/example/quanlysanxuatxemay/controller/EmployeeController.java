package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.Employee;
import org.example.quanlysanxuatxemay.model.EmployeeDTO;
import org.example.quanlysanxuatxemay.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDTOPage = employeeService.getEmployees(pageable, keyword);
        return new ResponseEntity<>(employeeDTOPage, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee
    ) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable String employeeId
    ) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employee), HttpStatus.OK);
    }
}
