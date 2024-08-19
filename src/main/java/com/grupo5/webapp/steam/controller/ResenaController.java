package com.grupo5.webapp.steam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupo5.webapp.steam.model.Resena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo5.webapp.steam.service.ResenaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RestController
@RequestMapping(value = "")
public class ResenaController {
    @Autowired
    ResenaService resenaService;

    @GetMapping("/resenas")
    public ResponseEntity<List<Resena>> listarResenas() {
        try {
            return ResponseEntity.ok(resenaService.listarResenas());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/resena")
    public ResponseEntity<Resena>buscarResenaPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(resenaService.buscarResenaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
   
    @PostMapping("/resena")
    public ResponseEntity<Map<String, String>> agregarResena(@RequestBody Resena resena){
        Map<String,String> response = new HashMap<>();
        try {
            if (resenaService.guardarResena(resena)) {
                response.put("msg", "La Reseña Se Agrego Con Exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("msg", "El comentario de tu Reseña contiene un mensaje agresivo :[");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo Un Error al crear La Reseña");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/resena")
    public ResponseEntity<Map<String, String>>editarResena(@RequestParam Long id, @RequestBody Resena resenaNew){
        Map<String,String> response = new HashMap<>();
        try {
            Resena resena = resenaService.buscarResenaPorId(id);
            resena.setCalificacion(resenaNew.getCalificacion());
            resena.setComentario(resenaNew.getComentario());
            resena.setJuego(resenaNew.getJuego());
            resena.setUsuario(resenaNew.getUsuario());
            response.put("message", "La Reseña Se Edito Con Exito");
            return ResponseEntity.ok(response);
            /*if (resenaService.editarResena(resena)) {
                response.put("msg", "La Reseña Se Editó Con Exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("msg", "El comentario de tu Reseña contiene un mensaje agresivo");
                return ResponseEntity.badRequest().body(response);
            }*/
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar La Reseña");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/resena")
    public ResponseEntity<Map<String, String>>eliminarResena(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Resena resena = resenaService.buscarResenaPorId(id);
            resenaService.eliminarResena(resena); 
            response.put("message", "La Reseña Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar La Reseña");
            return ResponseEntity.badRequest().body(response);
        } 
    }
}
