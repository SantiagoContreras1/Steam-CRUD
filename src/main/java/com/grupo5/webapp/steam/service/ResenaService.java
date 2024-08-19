package com.grupo5.webapp.steam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Resena;
import com.grupo5.webapp.steam.repository.ResenaRepository;

@Service
public class ResenaService implements IResenaService {

    @Autowired
    ResenaRepository resenaRepository;

    @Override
    public List<Resena> listarResenas() {
        return resenaRepository.findAll();
    }

     @Override
    public Boolean guardarResena(Resena resena) {
        if (validarComentario(resena)) {
            resenaRepository.save(resena);
            return true; 
        }else{
            return false;
        }
    }

    @Override
    public Resena buscarResenaPorId(Long id) {
        return resenaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarResena(Resena resena) {
        resenaRepository.delete(resena);
    }

     @Override
    public Boolean validarComentario(Resena resena) {
        if (resena.getComentario() == null || resena.getComentario().trim().isEmpty()) {
            return false;
        }else{
            return true;
        }
    }


}
