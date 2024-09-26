package com.grupo5.webapp.steam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Usuario;
import com.grupo5.webapp.steam.repository.UsuarioRepository;
import com.grupo5.webapp.steam.utils.SteamAlert;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarUsuario(Usuario usuario) {
        if (verificarEdad(usuario)) {
            return false; // No se guarda el usuario, ya que es menor de 18 años
        }else{
            usuarioRepository.save(usuario);
            return true; // El usuario se guarda correctamente
        }
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Boolean verificarEdad(Usuario usuarioNew) {
        Integer edadNew = usuarioNew.getEdad();
        Boolean flag = false;

        if (edadNew < 18) {
            flag = true; //Si es menor bro
            SteamAlert.getInstance().mostrarAlertaInfo(103);
        }else{
            return false;

        }
        return flag;

    }


}
