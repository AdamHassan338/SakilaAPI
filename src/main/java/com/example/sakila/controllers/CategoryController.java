package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Category;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.CategoryInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.output.CategoryDetailsOutput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDetailsOutput> createActor(@Validated(ValidationGroup.Create.class) @RequestBody CategoryInput data){



        Optional<CategoryDetailsOutput> optionalOutput = categoryService.createCategory(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(optionalOutput.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Byte id){
        categoryService.deleteCategory(id);
    }
}
