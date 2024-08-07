package com.kevingutierrez.webapp.biblioteca.service;

import java.util.List;

import com.kevingutierrez.webapp.biblioteca.model.Empleado;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Long id);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpeleado(Empleado empleado);
}
