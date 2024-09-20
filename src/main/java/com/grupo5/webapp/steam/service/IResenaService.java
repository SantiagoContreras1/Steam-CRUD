package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Resena;

public interface IResenaService {

    public List<Resena>listarResenas();

    public Boolean guardarResena(Resena resena);

    public Resena buscarResenaPorId(Long id);

    public void eliminarResena(Resena resena);
    
    public Boolean validarComentario(Resena resena);
}