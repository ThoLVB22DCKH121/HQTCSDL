package org.example.quanlysanxuatxemay.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "id", length = 20)
    private String employeeId;

    @Column(name = "name", nullable = false)
    private String employeeName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "departmenId", nullable = false)
    private Department department;

    @ManyToMany(mappedBy = "employees")
    private List<ProductionStage> productionStages;
}
