package com.grupo5.webapp.steam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Juego;
import com.grupo5.webapp.steam.repository.JuegoRepository;

@Service
public class JuegoService implements IJuegoService{

    @Autowired
    JuegoRepository juegoRepository;

    @Override
    public List<Juego> listarJuegos() {
        return juegoRepository.findAll();
    }

    @Override
    public Juego guardarJuego(Juego juego) {
        return juegoRepository.save(juego);
    }

    @Override
    public Juego buscarJuegoPorId(Long id) {
        return juegoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarJuego(Juego juego) {
        juegoRepository.delete(juego);
    }

}
