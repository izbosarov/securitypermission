package com.example.securitypermission.repository;

import com.example.securitypermission.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByNameContainingIgnoreCase(String name);
}
