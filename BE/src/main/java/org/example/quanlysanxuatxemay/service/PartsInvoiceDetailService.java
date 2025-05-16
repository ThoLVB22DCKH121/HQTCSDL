package org.example.quanlysanxuatxemay.service;
import org.example.quanlysanxuatxemay.repository.PartsInvoiceDetailRepository;
import org.example.quanlysanxuatxemay.model.PartImportStatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class PartsInvoiceDetailService {
    @Autowired
    private PartsInvoiceDetailRepository partsInvoiceDetailRepository;

    public List<PartImportStatDTO> getPartImportStats() {
        List<Object[]> results = partsInvoiceDetailRepository.findPartImportStats();
        List<PartImportStatDTO> dtos = new ArrayList<>();
        for (Object[] row : results) {
            String id = (String) row[0];
            String name = (String) row[1];
            Long quantity = (Long) row[2];
            Long cost = (Long) row[3];
            dtos.add(new PartImportStatDTO(id, name, quantity, cost));
        }
        return dtos;
    }
}
