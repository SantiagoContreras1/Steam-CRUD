package com.grupo5.webapp.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.webapp.steam.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByJuegoCategoria(String juegoCategoria);
}

