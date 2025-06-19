package org.example.quanlysanxuatxemay.entity;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInvoice {
    @Id
    @Column(name = "productInvoiceId", length = 20)
    private String productInvoiceId;

    @Column(name = "totalAmount", nullable = false)
    private Long totalAmount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @JsonIgnore
    @OneToMany(mappedBy = "productInvoice")
    private List<ProductInvoiceDetail> details;
}