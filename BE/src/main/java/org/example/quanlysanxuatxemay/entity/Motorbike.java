package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "motorbike")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorbike {

    @Id
    @Column(name = "id", length = 20)
    private String motorbikeId;

    @Column(name = "name", nullable = false, length = 255)
    private String motorbikeName;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "type", nullable = false, length = 255)
    private String type;
}