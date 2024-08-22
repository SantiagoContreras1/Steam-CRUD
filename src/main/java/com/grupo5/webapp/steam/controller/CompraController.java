package com.grupo5.webapp.steam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo5.webapp.steam.model.Compra;
import com.grupo5.webapp.steam.service.CompraService;

@Controller
@RestController
@RequestMapping(value = "")
public class CompraController {

    @Autowired
    CompraService compraService;

    @GetMapping("/compras")
    public ResponseEntity<List<Compra>>listarCompra(){
        try {
            return ResponseEntity.ok(compraService.listarCompras());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/compra")
    public ResponseEntity<Compra>buscarCompraPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(compraService.buscarCompraPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/compra")
    public ResponseEntity<Map<String, String>> agregarCompra(@RequestBody Compra compra){
        Map<String,String> response = new HashMap<>();
        try {
            if (compraService.guardarCompra(compra)) {
                response.put("msg", "La compra se agrego con exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("msg", "El numero de la tarjeta es invalido");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo Un Error al realizar la compra");
            return ResponseEntity.badRequest().body(response);
        }
    }

   
    

}