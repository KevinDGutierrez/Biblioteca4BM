package com.kevingutierrez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long>{

}
