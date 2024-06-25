package com.example.sakila.repository;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short> {
    @Query("SELECT a from Actor a WHERE LOWER(a.firstName) LIKE LOWER(:firstName)")
    Page<Actor> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
    Page<Actor> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}
