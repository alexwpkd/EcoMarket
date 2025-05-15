package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VentasRepository {

    private List<EmpleadoVentas> empVentas = new ArrayList<>();

    public List<EmpleadoVentas> obtenerEmpVentas(){
        return empVentas;
    }

    public EmpleadoVentas guardarEmpleadoV(EmpleadoVentas emp){
        empVentas.add(emp);
        return emp;
    }

    public EmpleadoVentas actualizarEmpleado(EmpleadoVentas emp){
        for(int i = 0; i < empVentas.size(); i++){
            if(empVentas.get(i).getCorreo().equals(emp.getCorreo())){
                empVentas.set(i,emp);
                return emp;
            }
        }
        return null;
    }

    public EmpleadoVentas buscarPorCorreo(String correo){
        for(EmpleadoVentas emp: empVentas){
            if (emp.getCorreo().equals(correo)){
                return emp;
            }
        }
        return null;
    }

    public String eliminarEmpleado(String correo){
        EmpleadoVentas emp = buscarPorCorreo(correo);
        if(emp!= null){
            empVentas.remove(emp);
            return "Empleado de ventas eliminado";
        }
        return "No se ha encontrado el empleado de ventas.";
    }

    public EmpleadoVentas inicioSesion(String correo, String contra){
        EmpleadoVentas emp = buscarPorCorreo(correo);
        if(emp!=null && emp.getContraseña().equals(contra)){
            return emp;
        }
        return null;
    }

}
