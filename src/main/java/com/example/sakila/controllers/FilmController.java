package com.example.sakila.controllers;


import com.example.sakila.entities.Film;
import com.example.sakila.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getFilms(){
        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    public Film getActorByID(@PathVariable Short id){
        return filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No film with id: %d",id)));
    }

}
