package com.prog4.ejercicio10.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.prog4.ejercicio10.model.Producto;
import com.prog4.ejercicio10.repositories.IProductoRepository;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductoRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Mouse");

        when(repository.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Mouse"));
    }

    @Test
    void guardarProducto() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Mouse");

        when(repository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(producto);

        mockMvc.perform(post("/productos")
                //.contentType("application/json")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Mouse"));
    }
}