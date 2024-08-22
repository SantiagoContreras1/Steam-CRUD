package com.grupo5.webapp.steam.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String mail;
    private Date fechaRegistro;
    private String psswd;
    private Integer edad;
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/SantiagoContreras
