package com.prog4.ejercicio10.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prog4.ejercicio10.model.Producto;
import com.prog4.ejercicio10.repositories.IProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {
    @Mock
    private IProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    // Happy Path: crear producto correctamente
    @Test
    void crearProductoCorrectamente() {
        Producto producto = new Producto(1L, "Mouse", 1500.0);

        when(repository.save(producto)).thenReturn(producto);

        Producto resultado = service.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNombre());
        assertEquals(1500.0, resultado.getPrecio());

        verify(repository).save(producto);
    }

    // Happy Path: listado vacío
    @Test
    void listarProductosVacio() {
        when(repository.findAll()).thenReturn(List.of());

        Iterable<Producto> resultado = service.listar();

        assertNotNull(resultado);
        assertEquals(0, ((List<?>) resultado).size());

        verify(repository).findAll();
    }

    @Test
    void guardarProducto() {

        Producto producto = new Producto(
                1L,
                "Mouse",
                1500.0);

        when(repository.save(producto))
                .thenReturn(producto);

        Producto resultado = service.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Mouse", resultado.getNombre());

        verify(repository).save(producto);
    }

    // Happy Path: listado con productos
    @Test
    void listarProductos() {

        List<Producto> productos = Arrays.asList(
                new Producto(1L, "Mouse", 1500.0),
                new Producto(2L, "Teclado", 3000.0));

        when(repository.findAll())
                .thenReturn(productos);

        Iterable<Producto> resultado = service.listar();

        assertNotNull(resultado);

        verify(repository).findAll();
    }

    // Negative: precio negativo
    @Test
    void guardarProductoConPrecioNegativo() {
        Producto producto = new Producto();
        producto.setNombre("Mouse");
        producto.setPrecio(-1500.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(producto);
        });

        assertEquals("El precio debe ser mayor a 0", ex.getMessage());
    }

    // Negative: nombre vacío
    @Test
    void crearProductoSinNombre() {
        Producto producto = new Producto(1L, "", 2500.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(producto);
        });

        assertEquals("El nombre no puede estar vacío", ex.getMessage());
    }

    // Boundary Testing (precio = 0)
    @Test
    void guardarProductoConPrecioCero() {
        Producto producto = new Producto();
        producto.setNombre("Mouse");
        producto.setPrecio(0.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(producto);
        });

        assertEquals("El precio debe ser mayor a 0", ex.getMessage());
    }

    // Boundary Testing (nombre de 1 caracter)
    @Test
    void crearProductoNombreUnCaracter() {
        Producto producto = new Producto(1L, "A", 500.0);

        when(repository.save(producto)).thenReturn(producto);

        Producto resultado = service.guardar(producto);

        assertNotNull(resultado);
        assertEquals("A", resultado.getNombre());

        verify(repository).save(producto);
    }
}
