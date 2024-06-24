package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;


import static com.example.sakila.enums.Rating.enumToRating;

@Getter
public class FilmDetailsOutput {

    private Short id;
    private String title;
    private String description;
    private Year year;
    private Language language;
   private Set<Actor> actors;
    //private String language_name;
    private Byte originalLanguageID;
    private Byte rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;


    public FilmDetailsOutput(Film f){
        id = f.getId();
        title = f.getTitle();
        description = f.getDescription();
        year = f.getYear();
        language = f.getLanguage();
        actors = f.getActors();
        originalLanguageID = f.getOriginalLanguageID();
        rentalDuration = f.getRentalDuration();
        rentalRate = f.getRentalRate();
        length = f.getLength();
        replacementCost = f.getReplacementCost();
        rating = enumToRating(f.getRating());
        specialFeatures = f.getSpecialFeatures();
    }


}
