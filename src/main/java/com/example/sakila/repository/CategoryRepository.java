package com.example.sakila.repository;

import com.example.sakila.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Byte> {

}
