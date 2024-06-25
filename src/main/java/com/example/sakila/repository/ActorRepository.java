package com.example.sakila.repository;

import com.example.sakila.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short> {
    List<Actor> findByFirstNameContainingIgnoreCase(String name);
}
