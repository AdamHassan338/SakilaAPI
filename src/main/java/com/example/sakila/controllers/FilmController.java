package com.example.sakila.controllers;


import com.example.sakila.input.FilmInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<FilmDetailsOutput> getFilms(@RequestParam("categoryName")  Optional<String> categoryName,@RequestParam("categoryID") Optional<Byte> categoryID ,@RequestParam("actorFullName")  Optional<String> actorFullName,
                                            @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "10") int size){

        if(categoryName.isPresent())
            return filmService.getFilmsInCatagoryName(categoryName.get(),page,size);
        if(categoryID.isPresent())
            return filmService.getFilmsInCatagoryid(categoryID.get(),page,size);
        if(actorFullName.isPresent()){
            System.out.printf(actorFullName.get());
            return filmService.getFilmsByActorFullName(actorFullName.get(),page,size);}
        return filmService.getFilms();
    }




    @GetMapping("/{id}")
    public ResponseEntity<FilmDetailsOutput> getFilmByID(@PathVariable Short id){
        Optional<FilmDetailsOutput> optionalFilm = filmService.getFilm(id);
        if(!optionalFilm.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalFilm.get());
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FilmDetailsOutput> createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput data){

        Optional<FilmDetailsOutput> optionalOutput = filmService.createFilm(data);

        if(!optionalOutput.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


        return ResponseEntity.status(HttpStatus.CREATED).body(optionalOutput.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilm(@PathVariable Short id){
        filmService.deleteFilm(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<FilmDetailsOutput> updateFilm(@PathVariable Short id, @Validated(ValidationGroup.Update.class) @RequestBody FilmInput data){
        Optional<FilmDetailsOutput> optionalOutput = filmService.updateFilm(id,data);

        if(!optionalOutput.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        return ResponseEntity.status(HttpStatus.OK).body(optionalOutput.get());
    }

}
