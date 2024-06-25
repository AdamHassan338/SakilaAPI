package com.example.sakila.services;

import com.example.sakila.entities.Category;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.enums.Rating;
import com.example.sakila.input.FilmInput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.repository.CategoryRepository;
import com.example.sakila.repository.FilmRepository;
import com.example.sakila.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.sakila.enums.Rating.ratingToEnum;
@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<FilmDetailsOutput> getFilms(){
        return filmRepository.findAll().stream().map(FilmDetailsOutput::new).toList();
    }

    public List<FilmDetailsOutput> getFilmsInCatagoryName(String catagoryName,int page, int size){
        return filmRepository.findByCategoryName(catagoryName.strip(), PageRequest.of(page,size)).stream().map(FilmDetailsOutput::new).toList();

    }
    public List<FilmDetailsOutput> getFilmsInCatagoryid(Byte id,int page,int size){
        return filmRepository.findByCategoryId(id, PageRequest.of(page,size)).stream().map(FilmDetailsOutput::new).toList();

    }

    public List<FilmDetailsOutput> getFilmsByActorFullName(String fullName,int page, int size){
        return filmRepository.findByActorFullName(fullName.strip(), PageRequest.of(page,size)).stream().map(FilmDetailsOutput::new).toList();
    }


    public List<FilmDetailsOutput> getFilmsInCatagoryName(Byte id,int page, int size){
        return filmRepository.findByCategoryId(id, PageRequest.of(page,size)).stream().map(FilmDetailsOutput::new).toList();

    }

    public Optional<FilmDetailsOutput> getFilm(Short id){
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if(!optionalFilm.isPresent())
            return Optional.empty();

        return Optional.of(new FilmDetailsOutput(optionalFilm.get()));
    }


    public Optional<FilmDetailsOutput> createFilm(FilmInput data){
        Film film = new Film();
        film.setDescription(data.getDescription());
        film.setLength(data.getLength());
        film.setRating(ratingToEnum(data.getRating()));
        film.setYear(data.getYear());
        {
            Optional<Language> language = languageRepository.findById(data.getLanguageID());
            if(!language.isPresent())
                return Optional.empty();
            film.setLanguage(language.get());
        }

        List<Category> temp = new ArrayList<>();
        for(Byte b : data.getCategories()){

            Optional<Category> category = categoryRepository.findById(b);
            if(!category.isPresent())
                return Optional.empty();
            temp.add(category.get());


        }
        film.setCategories(temp);

        film.setTitle(data.getTitle());
        film.setRentalRate(data.getRentalRate());
        film.setRentalDuration(data.getRentalDuration());
        film.setOriginalLanguageID(data.getOriginalLanguageID());
        film.setReplacementCost(data.getReplacementCost());
        film.setSpecialFeatures(data.getSpecialFeatures());
        film.setRentalDuration(data.getRentalDuration());

        if(film.getRating()== Rating.INVALID)
            return Optional.empty();

        film = filmRepository.save(film);

        return Optional.of(new FilmDetailsOutput(film));
    }

    public void deleteFilm(Short id){
        Optional<Film> film = filmRepository.findById(id);
        if(film.isPresent())
            filmRepository.delete(film.get());
    }

    public Optional<FilmDetailsOutput> updateFilm(Short id, FilmInput data){
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if(!optionalFilm.isPresent()) {
            return Optional.empty();
        }
        Film found = optionalFilm.get();

        if(data.getTitle()!=null)
            found.setTitle(data.getTitle());

        if(data.getDescription()!=null)
            found.setDescription(data.getDescription());
        if(data.getYear()!=null)
            found.setYear(data.getYear());

        if(data.getLanguageID()!=null){
            Optional<Language> language = languageRepository.findById(data.getLanguageID());
            if(!language.isPresent())
                return Optional.empty();
            found.setLanguage(language.get());
        }

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
            found.setRating( ratingToEnum(data.getRating()));

        if(data.getSpecialFeatures()!=null)
            found.setSpecialFeatures(data.getSpecialFeatures());

        if(found.getRating()== Rating.INVALID)
            return Optional.empty();

        List<Category> temp = new ArrayList<>();
        for(Byte b : data.getCategories()){

                Optional<Category> category = categoryRepository.findById(b);
                if(!category.isPresent())
                    return Optional.empty();
                temp.add(category.get());


        }
        found.setCategories(temp);

        filmRepository.save(found);

        return Optional.of(new FilmDetailsOutput(found));
    }


}
