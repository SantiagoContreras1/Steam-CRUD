package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Juego;

public interface IJuegoService {
    
    public List<Juego>listarJuegos();

    public Boolean guardarJuego(Juego juego);

    public Juego buscarJuegoPorId(Long id);

    public void eliminarJuego(Juego juego);

    public Boolean validarNombre(Juego juego);
}
