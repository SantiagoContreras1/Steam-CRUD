package com.grupo5.webapp.steam.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Desarrollador;
import com.grupo5.webapp.steam.repository.DesarrolladorRepository;
@Service
public class DesarrolladorService implements IDesarrolladorService{
    @Autowired
    private DesarrolladorRepository desarrolladorRepository;
    @Override

    public List<Desarrollador> listarDesarrolladores() {
        return desarrolladorRepository.findAll();
    }

    @Override
    public Desarrollador buscarDesarrolladorPorId(Long id) {
        return desarrolladorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarDesarrollador(Desarrollador desarrollador) {
        desarrolladorRepository.delete(desarrollador);        
    }

    @Override
    public Boolean guardarDesarrollador(Desarrollador desarrollador) {
        if(!verificarDesarrolladorDuplicado(desarrollador)){
            desarrolladorRepository.save(desarrollador);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean verificarDesarrolladorDuplicado(Desarrollador desarrolladorNuevo) {
        List<Desarrollador> desarrolladores = listarDesarrolladores();
        Boolean flag = false;
        for(Desarrollador desarrollador: desarrolladores){
            if(desarrollador.getNombre().equals(desarrolladorNuevo.getNombre())&&!desarrollador.getNombre().equals(desarrolladorNuevo.getNombre())){
                return true;
            }
        }
        return flag;
    }
}
