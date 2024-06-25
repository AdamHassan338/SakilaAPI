package com.example.sakila.repository;

import com.example.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Short> {
    @Query("SELECT f FROM Film f JOIN f.categories c WHERE c.name = :categoryName")
    List<Film> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT f FROM Film f JOIN f.categories c WHERE c.id = :categoryId")
    List<Film> findByCategoryId(@Param("categoryId") Byte categoryId);



}
