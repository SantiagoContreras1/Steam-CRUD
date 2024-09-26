package com.grupo5.webapp.steam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Categoria;
import com.grupo5.webapp.steam.repository.CategoriaRepository;
import com.grupo5.webapp.steam.utils.SteamAlert;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarCategoria(Categoria categoria) {
        if (!verificarCategoriaDuplicada(categoria)) {
            categoriaRepository.save(categoria);
            return true;
        }
        return false; 
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
    
    public Optional<Categoria> buscarCategoriaPorNombre(String juegoCategoria) {
        return categoriaRepository.findByJuegoCategoria(juegoCategoria);
    }

    @Override
    public Boolean verificarCategoriaDuplicada(Categoria nuevaCategoria) {
        List<Categoria> categorias = listarCategorias();
        for (Categoria categoria : categorias) {
            if (categoria.getJuegoCategoria().equals(nuevaCategoria.getJuegoCategoria()) 
                && !categoria.getId().equals(nuevaCategoria.getId())) {
                
                SteamAlert.getInstance().mostrarAlertaInfo(106);
                return true;
            }
        }
        return false;
    }

}
