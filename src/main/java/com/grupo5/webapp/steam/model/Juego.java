package com.grupo5.webapp.steam.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private Date fechaDeLanzamiento;
    private Double precio;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    @ManyToMany
    @JoinTable(name = "juegos_desarrolladores",
    joinColumns = @JoinColumn(name = "juego_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "desarrollo_id", referencedColumnName = "id"))
    private List<Desarrollador> desarrolladores;
}