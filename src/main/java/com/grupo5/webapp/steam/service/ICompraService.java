package com.grupo5.webapp.steam.service;

import java.util.List;

import com.grupo5.webapp.steam.model.Compra;

public interface ICompraService {
    public List<Compra> listarCompras();

    public Compra buscarCompraPorId(long id);

    public Boolean guardarCompra(Compra compra);

    public void eliminarCompra(Compra compra);

    public Boolean validarTarjeta(Compra compra);
}