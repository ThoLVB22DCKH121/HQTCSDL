package org.example.quanlysanxuatxemay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partsinvoice_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartsInvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partsInvoiceId", nullable = false)
    private PartsInvoice partsInvoice;

    @OneToOne
    @JoinColumn(name = "partId", nullable = false)
    private Part part;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unitPrice", nullable = false)
    private Long unitPrice;
}
