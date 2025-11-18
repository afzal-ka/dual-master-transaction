package com.example.two_master_one_blank.repo;

import com.example.two_master_one_blank.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
}
