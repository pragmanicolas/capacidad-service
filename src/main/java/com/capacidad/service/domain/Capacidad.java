package com.capacidad.service.domain;

import java.util.List;

public class Capacidad {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Long> tecnologiaIds; // Solo almacenamos los IDs de las tecnologías

    public Capacidad(Long id, String nombre, String descripcion, List<Long> tecnologiaIds) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnologiaIds = tecnologiaIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Long> getTecnologiaIds() {
        return tecnologiaIds;
    }

    public void setTecnologiaIds(List<Long> tecnologiaIds) {
        this.tecnologiaIds = tecnologiaIds;
    }
}
