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

import com.kevingutierrez.webapp.biblioteca.model.Empleado;
import com.kevingutierrez.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping("")
public class EmpleadoController {
    
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listaEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@RequestParam Long id){
        try {
           Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
           return ResponseEntity.ok(empleado); 
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String, String> response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "Empleado creado con éxito!!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el empeleado");
            return ResponseEntity.badRequest().body(response);
           
        }
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>> editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleado.setNombre(empleadoNuevo.getNombre());
            empleado.setApellido(empleadoNuevo.getApellido());
            empleado.setTelefono(empleadoNuevo.getTelefono());
            empleado.setDireccion(empleadoNuevo.getDireccion());
            empleado.setDpi(empleadoNuevo.getDpi());
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "La categoria ha sido modificada");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("messager", "Error");
            response.put("err", "Hubo un error");
            return ResponseEntity.badRequest().body(response);
        }    
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>> eliminarEmpeleado(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
           Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
           empleadoService.eliminarEmpeleado(empleado); 
           response.put("message", "Empleado eliminado");
           return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mess", "Error");
            response.put("err", "EL Empleado no se Elimino");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
