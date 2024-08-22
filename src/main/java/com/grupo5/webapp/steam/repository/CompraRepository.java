package com.grupo5.webapp.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.webapp.steam.model.Compra;

public interface CompraRepository extends JpaRepository<Compra,Long> {
    
}
