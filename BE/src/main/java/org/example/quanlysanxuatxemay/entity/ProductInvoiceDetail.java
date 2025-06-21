package org.example.quanlysanxuatxemay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productinvoice_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "productInvoiceId", nullable = false)
    private ProductInvoice productInvoice;

    @OneToOne
    @JoinColumn(name = "motorbikeId", nullable = false)
    private Motorbike motorbike;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitPrice", nullable = false)
    private Long unitPrice;
}
