package com.example.sakila.output;

import com.example.sakila.entities.Category;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import static com.example.sakila.enums.Rating.enumToRating;

@Getter
@Setter
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
    //private String rating;
    private String specialFeatures;
    private List<ShortCategoryDetailsOutput> categories;


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
        //rating = enumToRating(f.getRating());
        specialFeatures = f.getSpecialFeatures();
        categories = f.getCategories().stream().map(ShortCategoryDetailsOutput::new).toList();
    }


}
