package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.HoaDonLinhKien;
import org.example.quanlysanxuatxemay.entity.HoaDonSanPham;
import org.example.quanlysanxuatxemay.repository.HoaDonLinhKienRepository;
import org.example.quanlysanxuatxemay.repository.HoaDonSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonLinhKienRepository hoaDonLinhKienRepository;
    @Autowired
    private HoaDonSanPhamRepository hoaDonSanPhamRepository;

    public Page<HoaDonLinhKien> getHoaDonLinhKien(Pageable pageable) {
        return hoaDonLinhKienRepository.findAll(pageable);
    }

    public Page<HoaDonSanPham> getHoaDonSanPham(Pageable pageable) {
        return hoaDonSanPhamRepository.findAll(pageable);
    }
}
