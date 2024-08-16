package com.grupo5.webapp.steam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo5.webapp.steam.model.Juego;
import com.grupo5.webapp.steam.service.JuegoService;

@Controller
@RestController
@RequestMapping(value = "")
public class JuegoController {

    @Autowired
    JuegoService juegoService;

    @GetMapping("/juegos")
    public ResponseEntity<List<Juego>>listarJuegos(){
        try {
            return ResponseEntity.ok(juegoService.listarJuegos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/juego")
    public ResponseEntity<Juego>buscarJuegoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(juegoService.buscarJuegoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/juego")
    public ResponseEntity<Map<String, String>> agregarJuego(@RequestBody Juego juego){
        Map<String,String> response = new HashMap<>();
        try {
            juegoService.guardarJuego(juego);
            response.put("message", "El Juego Se Agrego Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo Un Error Al Crear El Juego");
            return ResponseEntity.badRequest().body(response);
        }
    }

     @PutMapping("/juego")
    public ResponseEntity<Map<String, String>>editarJuego(@RequestParam Long id, @RequestBody Juego juegoNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Juego juego = juegoService.buscarJuegoPorId(id);
            juego.setNombre(juegoNuevo.getNombre());
            juego.setDescripcion(juegoNuevo.getDescripcion());
            juego.setFechaDeLanzamiento(juegoNuevo.getFechaDeLanzamiento());
            juego.setPrecio(juegoNuevo.getPrecio());
            juegoService.guardarJuego(juego);
            response.put("message", "El Juego Se Edito Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar El Juego");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/juego")
    public ResponseEntity<Map<String, String>>eliminarJuego(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Juego juego = juegoService.buscarJuegoPorId(id);
            juegoService.eliminarJuego(juego); 
            response.put("message", "El Juego Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar El Juego");
            return ResponseEntity.badRequest().body(response);
        }
        

    }

}
