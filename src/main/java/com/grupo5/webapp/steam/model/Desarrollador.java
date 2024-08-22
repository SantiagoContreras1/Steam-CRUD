package com.grupo5.webapp.steam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "Desarrolladores")
public class Desarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
    private String nombre;
    private String pais;
}
=======

    private String nombre;
    private String pais;
}
>>>>>>> origin/RobbinSisimit
=======
    private String nombre;
    private String pais;
}
>>>>>>> origin/ManuelTejeda
