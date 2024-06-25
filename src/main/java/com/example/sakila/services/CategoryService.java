package com.example.sakila.services;

import com.example.sakila.entities.Category;
import com.example.sakila.input.CategoryInput;
import com.example.sakila.output.CategoryDetailsOutput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<CategoryDetailsOutput> createCategory(CategoryInput data){
        Category category = new Category();
        if(data.getName().length()>0)
            category.setName(data.getName());
        categoryRepository.save(category);
        return Optional.of(new CategoryDetailsOutput(category));

    }

    public List<CategoryDetailsOutput> getCategories(){
        return categoryRepository.findAll().stream().map(CategoryDetailsOutput::new).toList();
    }

    public Optional<CategoryDetailsOutput> getCategory(byte id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty())
            return Optional.empty();
        return Optional.of(new CategoryDetailsOutput(optionalCategory.get()));
    }


    public void deleteCategory(byte id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent())
            categoryRepository.delete(optionalCategory.get());
    }



}
