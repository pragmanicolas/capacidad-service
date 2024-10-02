package com.capacidad.service.adapters.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("capacidades")
public class CapacidadEntity {

    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Long> tecnologiaIds; // Almacenamos los IDs de las tecnologías asociadas a esta capacidad

    public CapacidadEntity(Long id, String nombre, String descripcion, List<Long> tecnologiaIds) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnologiaIds = tecnologiaIds;
    }

    // Getters y Setters
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
