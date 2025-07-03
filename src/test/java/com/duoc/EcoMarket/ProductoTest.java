package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.ProductoRepository;
import com.duoc.EcoMarket.services.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @Test
    @DisplayName("Crear nuevo producto")
    void testCrearProducto() {
        Producto producto = new Producto();
        producto.setNombre("Jugo Natural");
        producto.setCategoria("Bebestibles");
        producto.setDescripcion("Jugo de frutas sin azúcar");
        producto.setPrecio(1500);
        producto.setStock(30);

        Producto guardado = productoService.guardarProducto(producto);

        assertNotNull(guardado.getId());
        assertEquals("Jugo Natural", guardado.getNombre());
    }

    @Test
    @DisplayName("Buscar producto por ID")
    void testBuscarPorId() {
        Producto producto = new Producto();
        producto.setNombre("Yogurt Natural");
        producto.setCategoria("Lácteos");
        producto.setDescripcion("Yogurt bajo en grasa");
        producto.setPrecio(1000);
        producto.setStock(20);
        producto = productoRepository.save(producto);

        Optional<Producto> encontrado = productoService.buscarPorId(producto.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Yogurt Natural", encontrado.get().getNombre());
    }

    @Test
    @DisplayName("Buscar productos por nombre que contenga texto (ignorar mayúsculas)")
    void testBuscarPorNombre() {
        Producto producto = new Producto();
        producto.setNombre("Galletas Integrales");
        producto.setCategoria("Snacks");
        producto.setDescripcion("Sin azúcar añadida");
        producto.setPrecio(1200);
        producto.setStock(15);
        producto = productoService.guardarProducto(producto);

        List<Producto> resultados = productoService.buscarPorNombre("galletas");

        Long idBuscado = producto.getId(); // evitar error con lambda
        assertFalse(resultados.isEmpty());
        assertTrue(resultados.stream().anyMatch(p -> p.getId().equals(idBuscado)));
    }

    @Test
    @DisplayName("Buscar productos por categoría")
    void testBuscarPorCategoria() {
        Producto producto = new Producto();
        producto.setNombre("Leche Descremada");
        producto.setCategoria("Lácteos");
        producto.setDescripcion("Sin lactosa");
        producto.setPrecio(900);
        producto.setStock(40);
        producto = productoService.guardarProducto(producto);

        List<Producto> resultados = productoService.buscarPorCategoria("Lácteos");

        Long idBuscado = producto.getId(); // evitar error con lambda
        assertFalse(resultados.isEmpty());
        assertTrue(resultados.stream().anyMatch(p -> p.getId().equals(idBuscado)));
    }
}
