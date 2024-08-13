package com.kevingutierrez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevingutierrez.webapp.biblioteca.model.Categoria;
import com.kevingutierrez.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping("")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/categorias")
    public List<Categoria> listaCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id){
        try {
           Categoria categoria = categoriaService.buscarCategoriaPorId(id);
           return ResponseEntity.ok(categoria); 
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, String> response = new HashMap<>();
        try {
            if(!categoriaService.verificarCateoriaDuplicada(categoria)){
                categoriaService.guardarCategoria(categoria);
                response.put("message", "categoria creada con éxito!!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "Hubo un error Categoria duplicada");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear la categoria");
            return ResponseEntity.badRequest().body(response);
           
        }
    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            if(!categoriaService.verificarCateoriaDuplicada(categoria)){
            categoriaService.guardarCategoria(categoria);
            response.put("message", "La categoria ha sido modificada");
            return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "Hubo un error Categoria duplicada");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("messager", "Error");
            response.put("err", "Hubo un error");
            return ResponseEntity.badRequest().body(response);
        }    
    }

    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
           Categoria categoria = categoriaService.buscarCategoriaPorId(id);
           categoriaService.eliminarCategoria(categoria); 
           response.put("message", "categoria eliminada");
           return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mess", "Error");
            response.put("err", "La categoria no se le Elimino");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
