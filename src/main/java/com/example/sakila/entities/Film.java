package com.example.sakila.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.Year;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {

    public enum Rating {
        G,
        PG,
        PG13,
        R,
        NC17,
        INVALID
    }

    public static boolean isValidRating(String s){
        switch (s){
            case "G","PG","PG-13","R","NC-17":
                return true;
            default:
                return false;
        }
    }

    public static String enumToRating(Rating e){
        switch (e){
            case Rating.G:
                return "G";
            case Rating.PG:
                return "PG" ;
            case Rating.PG13:
                return "PG-13" ;
            case Rating.R:
                return "R" ;
            case Rating.NC17:
                return "NC-17" ;

        }
        return  new String();
    }

    public static Rating ratingToEnum(String s){
        switch (s){
            case "G":
                return Rating.G;
            case "PG":
                return Rating.PG;
            case "PG-13":
                return Rating.PG13;
            case "R":
                return Rating.R;
            case "NC-17":
                return Rating.NC17;

        }
        return Rating.INVALID;
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    @Column(name = "special_features")
    private String specialFeatures;


}
