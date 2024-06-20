package com.example.sakila.output;

import com.example.sakila.entities.Film;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
@Getter
public class FilmDetailsOutput {

    private Short id;
    private String title;
    private String description;
    private Year year;
    private Byte languageID;
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
        languageID = f.getLanguageID();
        originalLanguageID = f.getOriginalLanguageID();
        rentalDuration = f.getRentalDuration();
        rentalRate = f.getRentalRate();
        length = f.getLength();
        replacementCost = f.getReplacementCost();
        rating = f.getRating();
        specialFeatures = f.getSpecialFeatures();
    }
}
