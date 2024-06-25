package com.example.sakila.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static com.example.sakila.input.ValidationGroup.*;

@Data
public class FilmInput {

    @NotNull(groups = {Create.class})
    @Size(min =1,max=128)
    private String title;

    @Size(min =1)
    private String description;

    //@Size(min =1)
    private Year year;

    @NotNull(groups = {Create.class})
    //@Size(min =1,max=255)
    private Byte languageID;

    //@Size(min =1,max=255)
    private Byte originalLanguageID;

    @NotNull(groups = {Create.class})
    //@Size(min =1,max=255)
    private Byte rentalDuration = 3;

    @NotNull(groups = {Create.class})
   // @Size(min =0,max=100)
    private BigDecimal rentalRate = new BigDecimal(4.99);

    private Short length;

    @NotNull(groups = {Create.class})
    //@Size(min =1,max=100)
    private BigDecimal replacementCost = new BigDecimal(19.99);

    @Size(min=1 ,max = 5)
    private String rating = "G";

    @Size(min = 0, max = 200)
    private String specialFeatures;

    @Size(min =1)
    @NotNull(groups = {Create.class})
    private List<Byte> categories;


    private List<Short> actors = new ArrayList<>();


    public FilmInput(String title, String description, Year year, Byte languageID, Byte originalLanguageID, Byte rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost, String rating, String specialFeatures, List<Byte> categories, List<Short> actors) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.languageID = languageID;
        this.originalLanguageID = originalLanguageID;
        if(rentalDuration!= null)
            this.rentalDuration = rentalDuration;
        if(rentalRate!= null)
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        if(rating!= null)
            this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.categories = categories;
        this.actors = actors;
    }

}
