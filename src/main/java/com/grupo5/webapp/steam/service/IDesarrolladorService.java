package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Desarrollador;

public interface IDesarrolladorService {
    public List<Desarrollador> listarDesarrolladores();

    public Desarrollador buscarDesarrolladorPorId(Long id);

    public Boolean guardarDesarrollador(Desarrollador desarrollador);

    public void eliminarDesarrollador(Desarrollador desarrollador);

    public Boolean verificarDesarrolladorDuplicado(Desarrollador desarrolladorNuevo);
}
