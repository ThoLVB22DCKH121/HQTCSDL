package org.example.quanlysanxuatxemay.controller;

import org.example.quanlysanxuatxemay.entity.NhanVien;
import org.example.quanlysanxuatxemay.repository.NhanVienRepository;
import org.example.quanlysanxuatxemay.service.BoPhanService;
import org.example.quanlysanxuatxemay.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private BoPhanService boPhanService;

    @GetMapping("")
    public ResponseEntity<Page<NhanVien>> getNhanVien(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVien> nhanVienPage = nhanVienService.getNhanVien(pageable);
        return new ResponseEntity<>(nhanVienPage, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody NhanVien nhanVien) {
        return new ResponseEntity<>(nhanVienService.addNhanVien(nhanVien), HttpStatus.OK);
    }

    @DeleteMapping("/{maNhanVien}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable String maNhanVien) {
        nhanVienService.deleteNhanVienById(maNhanVien);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{maNhanVien}/bophan")
    public ResponseEntity<NhanVien> addNhanVienToBophan(
            @PathVariable String maNhanVien,
            @RequestParam(required = false) String nameBoPhan
    ) {
        NhanVien nhanvien = nhanVienRepository.findById(maNhanVien).get();
        nhanvien.setBoPhan(boPhanService.findByName(nameBoPhan));
        nhanVienRepository.save(nhanvien);
        return ResponseEntity.ok(nhanvien);
    }
}
