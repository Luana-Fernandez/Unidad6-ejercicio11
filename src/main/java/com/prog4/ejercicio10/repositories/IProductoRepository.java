package com.prog4.ejercicio10.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prog4.ejercicio10.model.Producto;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
    
}
