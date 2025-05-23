package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.*;
import com.duoc.EcoMarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentasRepository VR;

    @Autowired
    private PedidoRepository PER;

    @Autowired
    private ProductoRepository POR;

    @Autowired
    private ClienteRepository CR;

    @Autowired
    private EmpleadoVentasRepository EVR;

    // Registrar una venta a partir de un pedido existente
    public Venta registrarVenta(Long pedidoId, Long empleadoVentasId) {
        Optional<Pedido> pedidoOpt = PER.findById(pedidoId);
        Optional<EmpleadoVentas> empleadoOpt = EVR.findById(empleadoVentasId);

        if (!pedidoOpt.isPresent() || !empleadoOpt.isPresent()) {
            return null; // O lanzar excepción personalizada
        }

        Pedido pedido = pedidoOpt.get();
        EmpleadoVentas empleado = empleadoOpt.get();

        Venta venta = new Venta();
        venta.setCliente(pedido.getCliente());
        venta.setEmpleadoVentas(empleado);
        venta.setPedido(pedido);
        venta.setProductos(pedido.getProductos());

        // Calcular total sumando precios de productos
        double total = 0;
        for (Producto p : pedido.getProductos()) {
            total += p.getPrecio();
        }
        venta.setTotal(total);

        // Guardar venta
        return VR.save(venta);
    }

    // Consultar inventario (productos disponibles)
    public List<Producto> consultarInventario() {
        return POR.findAll();
    }

    // Puedes agregar método para generar factura básica (por ejemplo, un string con datos)
    public String generarFactura(Long ventaId) {
        Optional<Venta> ventaOpt = VR.findById(ventaId);
        if (!ventaOpt.isPresent()) return "Venta no encontrada";

        Venta venta = ventaOpt.get();

        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Venta ID: ").append(venta.getId()).append("\n");
        factura.append("Cliente: ").append(venta.getCliente().getNombre()).append("\n");
        factura.append("Empleado de Ventas: ").append(venta.getEmpleadoVentas().getNombre()).append("\n");
        factura.append("Productos:\n");
        for (Producto p : venta.getProductos()) {
            factura.append("- ").append(p.getNombre()).append(" : $").append(p.getPrecio()).append("\n");
        }
        factura.append("Total: $").append(venta.getTotal()).append("\n");

        return factura.toString();
    }
}
