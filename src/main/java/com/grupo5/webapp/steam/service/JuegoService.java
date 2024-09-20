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
    public Boolean guardarJuego(Juego juego) {
        if (validarNombre(juego)) {
            juegoRepository.save(juego);
            return true; 
        }else{
            return false;
        }
    }

    @Override
    public Juego buscarJuegoPorId(Long id) {
        return juegoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarJuego(Juego juego) {
        juegoRepository.delete(juego);
    }

    @Override
    public Boolean validarNombre(Juego juego) {
        if (juego.getNombre() == null || juego.getNombre().trim().isEmpty()) {
            return false;
        }else{
            return true;
        }
    }

}
