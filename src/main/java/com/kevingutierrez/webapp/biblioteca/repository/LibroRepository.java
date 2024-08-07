package com.kevingutierrez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
