package com.example.sakila.repository;

import com.example.sakila.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Short> {
    @Query("SELECT f FROM Film f JOIN f.categories c WHERE LOWER(c.name) = LOWER(:categoryName)")
    Page<Film> findByCategoryName(@Param("categoryName") String categoryNam, Pageable pageable);

    @Query("SELECT f FROM Film f JOIN f.categories c WHERE c.id = :categoryId")
    Page<Film> findByCategoryId(@Param("categoryId") Byte categoryId, Pageable pageable);


    @Query("SELECT f FROM Film f JOIN f.actors a WHERE LOWER(a.fullName) = LOWER(:fullName)")
    Page<Film> findByActorFullName(@Param("fullName") String fullName, Pageable pageable);
}
