package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "luong")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wage {

    @Column(name = "workday", nullable = false)
    private Long workday;

    @Column(name = "basic_salary", nullable = false)
    private Long basicSalary;

    @Column(name = "allowance", nullable = false)
    private Long allowance;

    @Id
    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;
}
