package com.kevingutierrez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
