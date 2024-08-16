package com.grupo5.webapp.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.webapp.steam.model.Juego;

public interface JuegoRepository extends JpaRepository<Juego, Long> {

}
