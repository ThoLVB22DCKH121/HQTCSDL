package org.example.quanlysanxuatxemay.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.example.quanlysanxuatxemay.enums.InvoiceStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partsinvoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartsInvoice {

    @Id
    @Column(name = "partsinvoiceId", length = 20)
    private String partsinvoiceId;

    @Column(name = "totalamount", nullable = false)
    private Long totalAmount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "partsInvoice")
    private List<PartsInvoiceDetail> details;
}