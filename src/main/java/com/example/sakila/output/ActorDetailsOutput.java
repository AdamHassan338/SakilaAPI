package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ActorDetailsOutput {
    private final Short id;
    private final String firstName;
    private final String lastName;
    private final List<ShortFilmOutput> films = new ArrayList<>();

    public ActorDetailsOutput(Actor a){
        this.id = a.getId();
        this.firstName = a.getFirstName();
        this.lastName = a.getLastName();
        for(Film f : a.getFilms())
        {
            films.add(new ShortFilmOutput(f));
        }
    }
}
