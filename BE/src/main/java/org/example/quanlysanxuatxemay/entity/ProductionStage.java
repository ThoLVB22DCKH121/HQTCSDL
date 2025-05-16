package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.time.LocalDate;

import org.example.quanlysanxuatxemay.enums.ProductionStatus;

@Entity
@Table(name = "production_stage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "motorbike_id")
    private Motorbike motorbike;

    @ManyToMany
    @JoinTable(
        name = "productionstage_employee",
        joinColumns = @JoinColumn(name = "productionstage_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    // Trạng thái quá trình sản xuất
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductionStatus status;

    @Column(name = "date")
    private LocalDate date;
}