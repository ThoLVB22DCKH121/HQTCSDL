package org.example.quanlysanxuatxemay.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @Column(name = "id", length = 20)
    private String supplierId;

    @Column(name = "name", nullable = false, length = 20)
    private String supplierName;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<PartsInvoice> partsInvoiceList;
}