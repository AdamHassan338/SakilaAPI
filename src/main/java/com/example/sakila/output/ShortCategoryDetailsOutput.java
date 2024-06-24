package com.example.sakila.output;

import com.example.sakila.entities.Category;
import lombok.Getter;

@Getter
public class ShortCategoryDetailsOutput {

    private Byte id;
    private String name;

    ShortCategoryDetailsOutput(Category category){
        id = category.getId();
        name = category.getName();
    }
}
