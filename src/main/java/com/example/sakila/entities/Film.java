package com.example.sakila.entities;
import com.example.sakila.enums.Rating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

@Entity
@Table(name = "film")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","actors"})
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

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonIgnore
    private Set<Actor> actors;

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
    private Rating rating;

    @Column(name = "special_features")
    private String specialFeatures;


}
