package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "part")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Part {
    @Id
    @Column(name = "id", length = 20)
    private String partId;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "name", nullable = false, length = 255)
    private String partName;
}
