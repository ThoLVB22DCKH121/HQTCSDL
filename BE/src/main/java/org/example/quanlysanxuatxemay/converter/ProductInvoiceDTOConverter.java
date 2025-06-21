package org.example.quanlysanxuatxemay.converter;

import org.example.quanlysanxuatxemay.entity.ProductInvoice;
import org.example.quanlysanxuatxemay.model.ProductInvoiceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductInvoiceDTOConverter {
    @Autowired
    ModelMapper modelMapper;
    public ProductInvoiceDTO toDTO(ProductInvoice productInvoice) {
        ProductInvoiceDTO dto = modelMapper.map(productInvoice, ProductInvoiceDTO.class);
        dto.setCustomerName(productInvoice.getCustomer().getCustomerName());
        dto.setProductInvoiceDetails(productInvoice.getDetails());
        return dto;
    }
}
