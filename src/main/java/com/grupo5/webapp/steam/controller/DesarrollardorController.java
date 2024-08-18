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

import com.grupo5.webapp.steam.model.Desarrollador;
import com.grupo5.webapp.steam.model.Usuario;
import com.grupo5.webapp.steam.service.DesarrolladorService;

@Controller
@RestController
@RequestMapping(value = "")
public class DesarrollardorController {
    @Autowired
    DesarrolladorService desarrolladorService;

    @GetMapping("/desarrolladores")
    public List<Desarrollador> listaDesarrolladores(){
        return desarrolladorService.listarDesarrolladores();
    }

    @GetMapping("/desarrollador")
    public ResponseEntity<Desarrollador> buscarDesarrolladorPorId(@RequestParam Long id){
        try{
            return ResponseEntity.ok(desarrolladorService.buscarDesarrolladorPorId(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/desarrollador")
    public ResponseEntity<Map<String, String>> agregarDesarrollador(@RequestBody Desarrollador desarrollador, @RequestBody Usuario usuario){
        Map<String, String> response = new HashMap<>();
        try{
            if(desarrolladorService.guardarDesarrollador(desarrollador)){
                response.put("message", "se agrego con exito el desarrollador");
                return ResponseEntity.ok(response);
            }else{
                response.put("error", "nose puede agregar");
                return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("message", "no se agrego :D");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/desarrollador")
    public ResponseEntity<Map<String, String>> editarDesarrollador(@RequestParam Long id, @RequestBody Desarrollador desarrolladorNuevo){
        Map<String, String> response = new HashMap<>();
        try{
            Desarrollador desarrollador = desarrolladorService.buscarDesarrolladorPorId(id);
            desarrollador.setNombre(desarrolladorNuevo.getNombre());
            desarrollador.setPais(desarrolladorNuevo.getPais());
            if(desarrolladorService.guardarDesarrollador(desarrollador)){
                response.put("message", "Se edito con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("err", "no se puedo editar");
                return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("err", "el desarrollador no se peude editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/desarrollador")
    public ResponseEntity<Map<String, String>> eliminarDesarrollador(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try{
            Desarrollador desarrollador = desarrolladorService.buscarDesarrolladorPorId(id);
            desarrolladorService.eliminarDesarrollador(desarrollador);
            response.put("message", "se elimino con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("error", "no se puede eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
