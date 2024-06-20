package com.example.sakila.controllers;


import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.FilmInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<FilmDetailsOutput> getFilms(){
        return filmRepository.findAll().stream().map(FilmDetailsOutput::new).toList();
    }

    @GetMapping("/{id}")
    public FilmDetailsOutput getFilmByID(@PathVariable Short id){
        return filmRepository.findById(id).map(FilmDetailsOutput::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No actor with id: %d",id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDetailsOutput createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput data){
        Film film = new Film();
        film.setDescription(data.getDescription());
        film.setLength(data.getLength());
        film.setRating(data.getRating());
        film.setYear(data.getYear());
        film.setLanguageID(data.getLanguageID());
        film.setTitle(data.getTitle());
        film.setRentalRate(data.getRentalRate());
        film.setRentalDuration(data.getRentalDuration());
        film.setOriginalLanguageID(data.getOriginalLanguageID());
        film.setReplacementCost(data.getReplacementCost());
        film.setSpecialFeatures(data.getSpecialFeatures());
        film.setRentalDuration(data.getRentalDuration());

        film = filmRepository.save(film);
        return new FilmDetailsOutput(film);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@PathVariable Short id){
        Optional<Film> film = filmRepository.findById(id);
        if(film.isPresent())
            filmRepository.delete(film.get());
    }

    @PatchMapping("{id}")
    public ResponseEntity<FilmDetailsOutput> updateActor(@PathVariable Short id, @Validated(ValidationGroup.Update.class) @RequestBody FilmInput data){
        Optional<Film> film = filmRepository.findById(id);

        if(!film.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Film found = film.get();

        if(data.getTitle()!=null)
            found.setTitle(data.getTitle());

        if(data.getDescription()!=null)
            found.setDescription(data.getDescription());
        if(data.getYear()!=null)
            found.setYear(data.getYear());

        if(data.getLanguageID()!=null)
            found.setLanguageID(data.getLanguageID());

        if(data.getOriginalLanguageID()!=null)
            found.setOriginalLanguageID(data.getOriginalLanguageID());

        if(data.getRentalDuration()!= null)
            found.setRentalDuration(data.getRentalDuration());

        if(data.getRentalRate()!= null)
            found.setRentalRate(data.getRentalRate());

        if(data.getLength()!=null)
            found.setLength(data.getLength());
        if(data.getReplacementCost()!=null)
            found.setReplacementCost(data.getReplacementCost());

        if(data.getRating()!= null)
            found.setRating(data.getRating());

        if(data.getSpecialFeatures()!=null)
            found.setSpecialFeatures(data.getSpecialFeatures());

        filmRepository.save(found);

        return ResponseEntity.status(HttpStatus.OK).body(new FilmDetailsOutput(found));
    }

}