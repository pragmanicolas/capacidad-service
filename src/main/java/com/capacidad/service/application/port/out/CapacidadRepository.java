package com.capacidad.service.application.port.out;

import com.capacidad.service.domain.Capacidad;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacidadRepository {

    Mono<Capacidad> save(Capacidad capacidad);

    Mono<Capacidad> findById(Long id);

    Flux<Capacidad> findAll();

    Mono<Void> deleteById(Long id);

    Flux<Capacidad> findAllSortedByName(boolean ascending, int page, int size);

    Flux<Capacidad> findAllSortedByTechnologiesCount(boolean ascending, int page, int size);

    Mono<Boolean> existsByName(String name);
}
