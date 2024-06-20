package com.example.sakila.converters;


import com.example.sakila.entities.Film;
import org.springframework.core.convert.converter.Converter;

import static com.example.sakila.entities.Film.enumToRating;

public class RatingConverter implements Converter<Film.Rating, String> {

    @Override
    public String convert(Film.Rating rating) {
        return enumToRating(rating);
    }
}
