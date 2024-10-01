package com.capacidad.service.adapters.in.web;

import com.capacidad.service.application.port.in.CapacidadService;
import com.capacidad.service.domain.Capacidad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/capacidades")
public class CapacidadController {

    private final CapacidadService capacidadService;

    public CapacidadController(CapacidadService capacidadService) {
        this.capacidadService = capacidadService;
    }

    // Endpoint para registrar una capacidad
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Capacidad>> registrarCapacidad(@RequestBody Capacidad capacidad) {
        return capacidadService.registrarCapacidad(capacidad)
                .map(savedCapacidad -> ResponseEntity.status(HttpStatus.CREATED).body(savedCapacidad))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    // Endpoint para listar capacidades con paginación y ordenación
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<Capacidad> listarCapacidades(
            @RequestParam(name = "ordenarPor", defaultValue = "nombre") String ordenarPor,
            @RequestParam(name = "ascendente", defaultValue = "true") boolean ascendente,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        // Cambia el orden de los parámetros en la llamada al servicio
        return capacidadService.listarCapacidades(ordenarPor,ascendente, page, size);
    }


    // Endpoint para obtener una capacidad por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Capacidad>> obtenerCapacidadPorId(@PathVariable Long id) {
        return capacidadService.obtenerCapacidadPorId(id)
                .map(capacidad -> ResponseEntity.ok(capacidad))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    // Endpoint para eliminar una capacidad por ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Void>> eliminarCapacidad(@PathVariable Long id) {
        return capacidadService.eliminarCapacidad(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))  // Especificamos explícitamente el tipo Void
                .onErrorResume(e -> Mono.just(ResponseEntity.<Void>notFound().build()));  // Especificamos explícitamente el tipo Void
    }

}
