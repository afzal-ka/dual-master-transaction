package com.example.two_master_one_blank.repo;

import com.example.two_master_one_blank.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products,Long> {
}
