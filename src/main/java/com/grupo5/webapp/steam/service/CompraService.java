package com.grupo5.webapp.steam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.webapp.steam.model.Compra;
import com.grupo5.webapp.steam.repository.CompraRepository;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Compra buscarCompraPorId(long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarCompra(Compra compra) {
        if (validarTarjeta(compra)) {
            compraRepository.save(compra);
            return true; 
        }else{
            return false;
        }
    }

    @Override
    public void eliminarCompra(Compra compra) {
        compraRepository.delete(compra);
    }

     @Override // algoritmo de Luhn
        public Boolean validarTarjeta(Compra compra) {
        int sum = 0;
        boolean alternador = false;
        String tarjeta = compra.getTarjeta();

        for (int i = tarjeta.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(tarjeta.substring(i, i + 1));

            if (alternador) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }

            sum += n;
            alternador = !alternador;
        }
        return (sum % 10 == 0);
    }
}
