package com.grupo5.webapp.steam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.webapp.steam.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

}
