package com.grupo5.webapp.steam.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Desarrollador;
import com.grupo5.webapp.steam.repository.DesarrolladorRepository;
import com.grupo5.webapp.steam.utils.SteamAlert;
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
        for(Desarrollador desarrollador : desarrolladores) {
            if(desarrollador.getNombre().equals(desarrolladorNuevo.getNombre()) 
            && !desarrollador.getId().equals(desarrolladorNuevo.getId())) {
                SteamAlert.getInstance().mostrarAlertaInfo(101);
                return true; 
            }
        }
        return false;
    }

}
