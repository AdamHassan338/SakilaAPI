package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


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

    @ManyToMany(mappedBy = "actors")
    private List<Film> films = new ArrayList<>();

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }


}
