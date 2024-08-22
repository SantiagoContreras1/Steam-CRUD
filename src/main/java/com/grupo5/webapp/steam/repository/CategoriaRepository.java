package com.grupo5.webapp.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.grupo5.webapp.steam.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByJuegoCategoria(String juegoCategoria);
}
