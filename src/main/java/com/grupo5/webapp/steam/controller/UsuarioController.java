package com.grupo5.webapp.steam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo5.webapp.steam.model.Usuario;
import com.grupo5.webapp.steam.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    //Listar
    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    //Buscar
    @GetMapping("/usuario")
    public ResponseEntity<Usuario> buscarUsuario(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/usuario")
    public ResponseEntity<Map<String,String>> agregarUsuario(@RequestBody Usuario usuario) {
        Map<String,String> response = new HashMap<>();

        try {
            if (usuarioService.guardarUsuario(usuario) == true) {
                response.put("msg", "Eres menor de edad! No puedes registrarte en Steam :[");
                return ResponseEntity.badRequest().body(response);
            }else{
                
                response.put("msg", "Se ha creado el usuario correctamente!");
                return ResponseEntity.ok(response);
                
            }

        } catch (Exception e) {
            response.put("mgs", "Error");
            response.put("err", "Hubo un error al crear al Usuario");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/usuario")
    public ResponseEntity<Map<String,String>> editarUsuario(@RequestParam Long id, @RequestBody Usuario usuarioNew) {
        Map<String,String> response = new HashMap<>();

        try {
            Usuario usuario = usuarioService.buscarUsuario(id);
            usuario.setNombre(usuarioNew.getNombre());
            usuario.setMail(usuarioNew.getMail());
            usuario.setFechaRegistro(usuarioNew.getFechaRegistro());
            usuario.setPsswd(usuarioNew.getPsswd());
            usuario.setEdad(usuarioNew.getEdad());
            usuarioService.guardarUsuario(usuario);

            response.put("msg", "Se editó al usuario exitosamente!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mgs", "Error");
            response.put("err", "Hubo un error al editar al Usuario");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/usuario")
    public ResponseEntity<Map<String,String>> eliminarUsuario(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();

        try {
            Usuario usuario = usuarioService.buscarUsuario(id);
            usuarioService.eliminarUsuario(usuario);
            response.put("msg", "Se eliminó al usuario correctamente!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("msg", "Error");
            response.put("err", "Hubo un error al eliminar al usuario!");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    

}
