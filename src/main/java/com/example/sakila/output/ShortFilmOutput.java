package com.example.sakila.output;

import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;

import static com.example.sakila.enums.Rating.enumToRating;

@Getter
public class ShortFilmOutput {

    private Short id;
    private String title;
    private String description;
    private Year year;
    private Language language;
    private Byte originalLanguageID;
    private Byte rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;


    public ShortFilmOutput(Film f){
        id = f.getId();
        title = f.getTitle();
        description = f.getDescription();
        year = f.getYear();
        language = f.getLanguage();
        originalLanguageID = f.getOriginalLanguageID();
        rentalDuration = f.getRentalDuration();
        rentalRate = f.getRentalRate();
        length = f.getLength();
        replacementCost = f.getReplacementCost();
        rating = enumToRating(f.getRating());
        specialFeatures = f.getSpecialFeatures();
    }


}
