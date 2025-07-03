package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.*;
import com.duoc.EcoMarket.repository.*;
import com.duoc.EcoMarket.services.VentaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class VentaTest {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentasRepository ventasRepository;

    @Test
    @Transactional
    @DisplayName("Registrar venta correctamente")
    void testRegistrarVenta() {
        // Cliente
        Cliente cliente = clienteRepository.findByCorreo("venta_cliente@correo.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNombre("Cliente Venta");
            cliente.setCorreo("venta_cliente@correo.com");
            cliente.setDireccion("Calle 1");
            cliente.setTelefono(12345678);
            cliente.setContraseña("clave");
            cliente = clienteRepository.save(cliente);
        }

        // Empleado de ventas
        EmpleadoVentas empleado = empleadoVentasRepository.findByCorreo("ventas@correo.com");
        if (empleado == null) {
            empleado = new EmpleadoVentas();
            empleado.setNombre("Empleado V");
            empleado.setCorreo("ventas@correo.com");
            empleado.setTelefono(99999999);
            empleado.setContraseña("abc123");
            empleado = empleadoVentasRepository.save(empleado);
        }

        // Productos
        Producto p1 = new Producto();
        p1.setNombre("Pan");
        p1.setPrecio(1000);
        p1.setCategoria("Alimentos");
        p1.setStock(10);
        p1.setDescripcion("Pan integral");
        p1 = productoRepository.save(p1);

        Producto p2 = new Producto();
        p2.setNombre("Leche");
        p2.setPrecio(1500);
        p2.setCategoria("Lácteos");
        p2.setStock(10);
        p2.setDescripcion("Leche descremada");
        p2 = productoRepository.save(p2);

        // Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("Listo");
        pedido.setTotal(0);
        pedido.setProductos(Arrays.asList(p1, p2));
        pedido = pedidoRepository.save(pedido);

        // Registrar venta
        Venta venta = ventaService.registrarVenta(pedido.getId(), empleado.getId());

        assertNotNull(venta);
        assertEquals(cliente.getId(), venta.getCliente().getId());
        assertEquals(empleado.getId(), venta.getEmpleadoVentas().getId());
        assertEquals(2500.0, venta.getTotal());
    }

    @Test
    @DisplayName("Generar factura correctamente")
    @Transactional
    void testGenerarFactura() {
        // Cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Cliente");
        cliente.setCorreo("cliente_factura@correo.com");
        cliente.setDireccion("Calle 123");
        cliente.setTelefono(12345678);
        cliente.setContraseña("clave");
        cliente = clienteRepository.save(cliente);

        // Empleado
        EmpleadoVentas empleado = new EmpleadoVentas();
        empleado.setNombre("Vendedor");
        empleado.setCorreo("factura@correo.com");
        empleado.setTelefono(12345678);
        empleado.setContraseña("clave");
        empleado = empleadoVentasRepository.save(empleado);

        Producto p1 = new Producto();
        p1.setNombre("Pan");
        p1.setDescripcion("Integral");
        p1.setCategoria("Alimentos");
        p1.setStock(10);
        p1.setPrecio(1000);

        Producto p2 = new Producto();
        p2.setNombre("Leche");
        p2.setDescripcion("Descremada");
        p2.setCategoria("Lácteos");
        p2.setStock(10);
        p2.setPrecio(1500);


        // Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("Entregado");
        pedido.setProductos(Arrays.asList(p1, p2));
        pedido.setTotal(0);
        pedido = pedidoRepository.save(pedido);

        // Venta
        Venta venta = ventaService.registrarVenta(pedido.getId(), empleado.getId());
        assertNotNull(venta);

        // Generar factura
        String factura = ventaService.generarFactura(venta.getId());

        assertNotNull(factura);
        assertTrue(factura.contains("Factura de Venta ID:"), "Debe contener ID");
        assertTrue(factura.contains("Cliente:"), "Debe contener cliente");
        assertTrue(factura.contains("Productos:"), "Debe contener productos");
        assertTrue(factura.contains("Total:"), "Debe contener total");
    }


    @Test
    @DisplayName("Consultar inventario")
    void testConsultarInventario() {
        List<Producto> inventario = ventaService.consultarInventario();

        assertNotNull(inventario);
        assertTrue(inventario.size() > 0);
    }

    @Test
    @DisplayName("Registrar venta con pedido inválido debe fallar")
    void testRegistrarVentaPedidoInvalido() {
        Venta venta = ventaService.registrarVenta(9999L, 1L); // ID de pedido inexistente
        assertNull(venta);
    }
}
