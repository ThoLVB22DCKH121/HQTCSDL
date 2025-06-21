package org.example.quanlysanxuatxemay.converter;

import org.example.quanlysanxuatxemay.entity.PartsInvoice;
import org.example.quanlysanxuatxemay.model.PartsInvoiceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartsInvoiceDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PartsInvoiceDTO toDTO(PartsInvoice partsInvoice) {
        PartsInvoiceDTO dto = modelMapper.map(partsInvoice, PartsInvoiceDTO.class);
        dto.setSupplierName(partsInvoice.getSupplier().getSupplierName());
        dto.setPartsInvoiceDetails(partsInvoice.getDetails());
        return dto;
    }
}
