/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DAPP01Practica05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author zaireko
 */
@RestController
@RequestMapping("/api/v1")
public class EmpleadosController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleado")
    public ResponseEntity<List<Empleado>> list() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> get(@PathVariable Long id) {
        Optional<Empleado> optEmpleado = empleadoRepository.findById(id);
        return optEmpleado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado empleado) {
        Optional<Empleado> optEmpleado = empleadoRepository.findById(id);
        if (optEmpleado.isPresent()) {
            Empleado existingEmpleado = optEmpleado.get();
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setDireccion(empleado.getDireccion());
            existingEmpleado.setTelefono(empleado.getTelefono());
            empleadoRepository.save(existingEmpleado);
            return ResponseEntity.ok(existingEmpleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> post(@RequestBody Empleado empleado) {
        Empleado savedEmpleado = empleadoRepository.save(empleado);
        return ResponseEntity.ok(savedEmpleado);
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Empleado> optEmpleado = empleadoRepository.findById(id);
        if (optEmpleado.isPresent()) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}