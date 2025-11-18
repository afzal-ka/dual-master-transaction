package com.example.two_master_one_blank.repo;

import com.example.two_master_one_blank.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
