package com.example.sakila.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "category")
@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Size(min = 1,max = 25)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Film> films = new ArrayList<>();

}
