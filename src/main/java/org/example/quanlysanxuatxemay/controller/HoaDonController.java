package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.HoaDonLinhKien;
import org.example.quanlysanxuatxemay.entity.HoaDonSanPham;
import org.example.quanlysanxuatxemay.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/linhkien")
    public ResponseEntity<Page<HoaDonLinhKien>> getHoadonLinhKien(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDonLinhKien> hoaDonLinhKienPage = hoaDonService.getHoaDonLinhKien(pageable);
        return ResponseEntity.ok(hoaDonLinhKienPage);
    }

    @GetMapping("/sanpham")
    public ResponseEntity<Page<HoaDonSanPham>> getHoadonSanPham(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<HoaDonSanPham> hoaDonSanPhamPage = hoaDonService.getHoaDonSanPham(pageable);
        return ResponseEntity.ok(hoaDonSanPhamPage);
    }
}
