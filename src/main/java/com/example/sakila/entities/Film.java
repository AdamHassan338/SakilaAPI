package com.example.sakila.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title")
    private String title;


    @Column(name = "description")
    private String description;


    @Column(name = "release_year")
    private Year year;

    @Column(name = "language_id")
    private Byte languageID;


    @Column(name = "original_language_id")
    private Byte originalLanguageID;


    @Column(name = "rental_duration")
    private Byte rentalDuration;


    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;


}
