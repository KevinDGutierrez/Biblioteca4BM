package com.kevingutierrez.webapp.biblioteca.controller;

import java.util.HashMap;
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

import com.kevingutierrez.webapp.biblioteca.model.Prestamo;
import com.kevingutierrez.webapp.biblioteca.service.PrestamoService;

@Controller
@RestController
@RequestMapping("")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<?> listarPrestamos(){
        Map<String, String> response = new HashMap<>();
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
           response.put("message", "Error");
           response.put("err", "No se encontro una lista prestamos");
           return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>> agreagarPrestamo(@RequestBody Prestamo prestamo){
        Map<String, String> response = new HashMap<>();
        try {
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "Prestamo creado con éxito !!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>> editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){

        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setFechaPrestamo(prestamoNuevo.getFechaPrestamo());
            prestamo.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamo.setLibros(prestamoNuevo.getLibros());
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "Prestamo editado con exito!!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err:", "Hubo un error al modificar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }        
    }

    @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("message", "prestamo eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Message", "Error");
            response.put("err", "Hubo un problema al eliminar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
