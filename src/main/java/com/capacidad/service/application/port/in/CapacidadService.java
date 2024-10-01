package com.capacidad.service.application.port.in;

import com.capacidad.service.domain.Capacidad;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacidadService {

    Mono<Capacidad> registrarCapacidad(Capacidad capacidad);

    Flux<Capacidad> listarCapacidades(String orderBy, boolean ascending, int page, int size);

    Mono<Capacidad> obtenerCapacidadPorId(Long id);

    Mono<Void> eliminarCapacidad(Long id);
}