package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Juego;

public interface IJuegoService {
    
    public List<Juego>listarJuegos();

    public Juego guardarJuego(Juego juego);

    public Juego buscarJuegoPorId(Long id);

    public void eliminarJuego(Juego juego);
}
