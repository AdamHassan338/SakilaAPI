package com.example.sakila.repository;

import com.example.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Short> {
}
