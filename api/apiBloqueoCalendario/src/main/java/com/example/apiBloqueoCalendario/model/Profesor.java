package com.example.apiBloqueoCalendario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "dni",nullable = false,unique = true)
    private String dni;

    @NotBlank
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "apellidos",nullable = false)
    private String apellidos;

    @NotBlank
    @Column(name = "cp",nullable = false)
    private String cp;

    @NotBlank
    @Column(name = "localidad",nullable = false)
    private String localidad;

    @NotBlank
    @Column(name = "telefono",nullable = false)
    private String telefono;

    @NotBlank
    @Column(name = "direccion",nullable = false)
    private String direccion;

    @NotBlank
    @Column(name = "cuerpo",nullable = false)
    private String cuerpo;

    @NotBlank
    @Column(name = "especialidad",nullable = false)
    private String especialidad;

    @NotBlank
    @Column(name = "departamento",nullable = false)
    private String departamento;

    public Profesor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
