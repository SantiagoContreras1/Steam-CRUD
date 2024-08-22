package com.grupo5.webapp.steam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo5.webapp.steam.model.Categoria;
import com.grupo5.webapp.steam.service.CategoriaService;

@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria) {
        Map<String, String> response = new HashMap<>();
        Optional<Categoria> categoriaExistente = categoriaService.buscarCategoriaPorNombre(categoria.getJuegoCategoria());
        
        if (categoriaExistente.isPresent()) {
            response.put("message", "La categoría del juego ya existe.");
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            categoriaService.guardarCategoria(categoria);
            response.put("message", "La categoría del juego se agregó con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo un error al crear la categoría del juego");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            
            Optional<Categoria> categoriaExistente = categoriaService.buscarCategoriaPorNombre(categoriaNueva.getJuegoCategoria());
            if (categoriaExistente.isPresent() && categoriaExistente.get().getId() == id) {
                response.put("message", "La categoría del juego ya existe.");
                return ResponseEntity.badRequest().body(response);
            }            
            
            categoria.setJuegoCategoria(categoriaNueva.getJuegoCategoria());
            categoriaService.guardarCategoria(categoria);
            response.put("message", "La categoría del juego se ha editado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "La categoría del juego no pudo editarse");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Categoría eliminada con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al eliminar la categoría");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
