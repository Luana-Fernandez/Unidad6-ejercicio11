// package com.prog4.ejercicio10.controller;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import com.prog4.ejercicio10.model.Producto;
// import com.prog4.ejercicio10.service.ProductoService;

// @WebMvcTest(ProductoController.class)
// public class ProductoControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private ProductoService service;

//     @Test
//     void obtenerProductos() throws Exception {

//         List<Producto> lista = List.of(
//                 new Producto(1L, "Mouse", 1500.0));

//         when(service.listar()).thenReturn(lista);

//         mockMvc.perform(get("/productos"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].nombre").value("Mouse"));
//     }

//     @Test
//     void guardarProducto() throws Exception {

//         Producto producto = new Producto(1L, "Mouse", 1500.0);

//         when(service.guardar(any())).thenReturn(producto);

//         mockMvc.perform(post("/productos")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("""
//                         {
//                           "nombre": "Mouse",
//                           "precio": 1500
//                         }
//                         """))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.nombre").value("Mouse"));
//     }

//     @Test
//     void guardarProducto_precioNegativo() throws Exception {

//         mockMvc.perform(post("/productos")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("""
//                         {
//                           "nombre": "Mouse",
//                           "precio": -10
//                         }
//                         """))
//                 .andExpect(status().isBadRequest());
//     }
// }