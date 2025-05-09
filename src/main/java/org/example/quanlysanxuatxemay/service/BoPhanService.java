package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.BoPhan;
import org.example.quanlysanxuatxemay.repository.BoPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoPhanService {
    @Autowired
    private BoPhanRepository boPhanRepository;

    public BoPhan findByName(String name) {
        return boPhanRepository.findByName(name);
    }
}
