package com.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

