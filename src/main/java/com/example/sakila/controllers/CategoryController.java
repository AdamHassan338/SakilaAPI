package com.example.sakila.controllers;

import com.example.sakila.entities.Category;
import com.example.sakila.output.CategoryDetailsOutput;
import com.example.sakila.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public List<CategoryDetailsOutput> getCategories(){
        return categoryService.getCategories();

    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsOutput> getCategoryByID(@PathVariable Byte id){
        Optional<CategoryDetailsOutput> optionalCategory = categoryService.getCategory(id);
        if(!optionalCategory.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalCategory.get());
    }
}
