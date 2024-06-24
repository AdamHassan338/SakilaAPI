package com.example.sakila.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

@Entity
@Table(name = "actor")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","films"})
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "actors")
    private Set<Film> films;

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }


}
