package com.example.apiBloqueoCalendario.controller;


import com.example.apiBloqueoCalendario.model.BloqueoCalendario;
import com.example.apiBloqueoCalendario.service.BloqueoCalendarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloqueo-calendario")
public class BloqueoCalendarioController {

    private BloqueoCalendarioService service;

    public BloqueoCalendarioController(BloqueoCalendarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<BloqueoCalendario> listar() {
        return service.listar();
    }

    @GetMapping("{id}")
    public BloqueoCalendario obtener(@PathVariable Long id){
        return service.obtener(id);
    }
    @PostMapping
    public ResponseEntity<BloqueoCalendario> crear(@Valid @RequestBody BloqueoCalendario b){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(b));
    }
    @PutMapping("/{id}")
    public BloqueoCalendario actualizar(@PathVariable Long id,@Valid @RequestBody BloqueoCalendario b){
        return service.actualizar(id,b);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
