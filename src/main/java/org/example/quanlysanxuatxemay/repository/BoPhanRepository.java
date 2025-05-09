package org.example.quanlysanxuatxemay.repository;

import org.example.quanlysanxuatxemay.entity.BoPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoPhanRepository extends JpaRepository<BoPhan, String> {
    public BoPhan findByName(String name);
}