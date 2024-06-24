package com.example.sakila.converters;



import com.example.sakila.enums.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import static com.example.sakila.enums.Rating.ratingToEnum;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if(rating==null)
            return null;
        return rating.getCode();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return ratingToEnum(s);
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
