package com.prog4.ejercicio10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog4.ejercicio10.model.Producto;
import com.prog4.ejercicio10.repositories.IProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private IProductoRepository repository;

    // public Producto guardar(Producto producto) {
    // return repository.save(producto);
    // }

    public Iterable<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto producto) {
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        return repository.save(producto);
    }
}
