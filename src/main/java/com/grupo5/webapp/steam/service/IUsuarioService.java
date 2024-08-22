package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Usuario;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuario(Long id);

    public Boolean guardarUsuario(Usuario usuario); //Usar para la validación

    public void eliminarUsuario(Usuario usuario);

    public Boolean verificarEdad(Usuario usuarioNew);
}
