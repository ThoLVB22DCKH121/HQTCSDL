package org.example.quanlysanxuatxemay.repository;

import java.util.List;

import org.example.quanlysanxuatxemay.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE :keyword IS NULL OR LOWER(e.employeeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Employee> getEmployees (
            Pageable pageable,
            @Param("keyword")String keyword
    );

    Employee findEmployeesByEmployeeId(String employeeId);

    void deleteEmployeeByEmployeeId(String employeeId);

    List<Employee> findAllByEmployeeIdIn(List<String> employeeIds);
}
