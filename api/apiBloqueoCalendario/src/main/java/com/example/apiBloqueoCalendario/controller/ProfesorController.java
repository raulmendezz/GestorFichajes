package com.example.apiBloqueoCalendario.controller;

import com.example.apiBloqueoCalendario.model.Profesor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private Profesor service;

    public ProfesorController(Profesor service) {
        this.service = service;
    }

    @GetMapping
    public List<Profesor>listar(){
        service.listar();
    }
}
