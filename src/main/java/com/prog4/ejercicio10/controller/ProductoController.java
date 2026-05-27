package com.prog4.ejercicio10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prog4.ejercicio10.model.Producto;
import com.prog4.ejercicio10.repositories.IProductoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")

public class ProductoController {

    @Autowired
    private IProductoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto guardar(@Valid @RequestBody Producto producto) {
        return repository.save(producto);
    }

    @GetMapping
    public Iterable<Producto> listar() {
        return repository.findAll();
    }
}
