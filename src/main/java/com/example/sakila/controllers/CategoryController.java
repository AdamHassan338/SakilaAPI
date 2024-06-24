package com.example.sakila.controllers;

import com.example.sakila.entities.Category;
import com.example.sakila.output.CategoryDetailsOutput;
import com.example.sakila.repository.CategoryRepository;
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
    CategoryRepository categoryRepository;
    @GetMapping
    public List<CategoryDetailsOutput> getCategories(){
        return categoryRepository.findAll().stream().map(CategoryDetailsOutput::new).toList();

    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsOutput> getFilmByID(@PathVariable Byte id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.FOUND).body(new CategoryDetailsOutput(optionalCategory.get()));
    }
}
