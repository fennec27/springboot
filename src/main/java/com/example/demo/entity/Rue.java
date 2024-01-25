package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Rue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="capital_id")
    @JsonIgnore
    private Capital capital;
}
