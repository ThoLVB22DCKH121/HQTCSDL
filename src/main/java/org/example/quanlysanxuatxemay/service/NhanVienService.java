package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.NhanVien;
import org.example.quanlysanxuatxemay.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public Page<NhanVien> getNhanVien(Pageable pageable) {
        return nhanVienRepository.findAll(pageable);
    }

    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public void deleteNhanVienById(String maNhanVien) {
        nhanVienRepository.deleteById(maNhanVien);
    }
}
