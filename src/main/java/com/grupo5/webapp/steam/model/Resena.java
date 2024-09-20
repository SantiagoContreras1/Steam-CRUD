package com.grupo5.webapp.steam.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Reseñas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long calificacion;
    @Column(columnDefinition = "TEXT")
    private String comentario;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    private Juego juego;
}

