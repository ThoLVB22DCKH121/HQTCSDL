package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill_of_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillOfMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "motorbikeId", nullable = false)
    private Motorbike motorbike;

    @OneToOne
    @JoinColumn(name = "partId", nullable = false)
    private Part part;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
