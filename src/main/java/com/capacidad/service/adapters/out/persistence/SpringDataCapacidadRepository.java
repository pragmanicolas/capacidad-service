package com.capacidad.service.adapters.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SpringDataCapacidadRepository extends ReactiveCrudRepository<CapacidadEntity, Long> {
    // Método para verificar si existe una capacidad con el nombre especificado
    Mono<Boolean> existsByNombre(String nombre);
}
