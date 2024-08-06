package com.kevingutierrez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {


}
