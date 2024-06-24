package com.example.sakila.output;

import com.example.sakila.entities.Category;
import com.example.sakila.entities.Film;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryDetailsOutput {
    private Byte id;
    private String name;
    private List<ShortFilmOutput> films = new ArrayList<>();

    public CategoryDetailsOutput(Category category){
        id = category.getId();
        name = category.getName();
        for(Film f : category.getFilms()){
            films.add(new ShortFilmOutput(f));
        }
    }
}
