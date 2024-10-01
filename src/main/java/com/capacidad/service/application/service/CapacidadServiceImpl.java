package com.capacidad.service.application.service;

import com.capacidad.service.application.exception.CapacidadAlreadyExistsException;
import com.capacidad.service.application.exception.InvalidCapacidadDataException;
import com.capacidad.service.application.port.in.CapacidadService;
import com.capacidad.service.application.port.out.CapacidadRepository;
import com.capacidad.service.domain.Capacidad;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;

@Service
public class CapacidadServiceImpl implements CapacidadService {

    private final CapacidadRepository capacidadRepository;

    public CapacidadServiceImpl(CapacidadRepository capacidadRepository) {
        this.capacidadRepository = capacidadRepository;
    }

    private Mono<Void> validateCapacidad(Capacidad capacidad) {
        if (capacidad.getNombre() == null || capacidad.getNombre().isEmpty()) {
            return Mono.error(new InvalidCapacidadDataException("El nombre de la capacidad es obligatorio."));
        }
        if (capacidad.getNombre().length() > 50) {
            return Mono.error(new InvalidCapacidadDataException("El nombre de la capacidad no puede tener más de 50 caracteres."));
        }
        if (capacidad.getDescripcion() == null || capacidad.getDescripcion().isEmpty()) {
            return Mono.error(new InvalidCapacidadDataException("La descripción de la capacidad es obligatoria."));
        }
        if (capacidad.getDescripcion().length() > 90) {
            return Mono.error(new InvalidCapacidadDataException("La descripción de la capacidad no puede tener más de 90 caracteres."));
        }
        if (capacidad.getTecnologiaIds() == null || capacidad.getTecnologiaIds().size() < 3) {
            return Mono.error(new InvalidCapacidadDataException("La capacidad debe tener al menos 3 tecnologías asociadas"));
        }

        if (capacidad.getTecnologiaIds().size() > 20) {
            return Mono.error(new InvalidCapacidadDataException("La capacidad no puede tener más de 20 tecnologías"));
        }

        // Validar si hay tecnologías repetidas
        if (capacidad.getTecnologiaIds().size() != new HashSet<>(capacidad.getTecnologiaIds()).size()) {
            return Mono.error(new InvalidCapacidadDataException("La capacidad no puede tener tecnologías repetidas"));
        }

        return Mono.empty();

    }

    @Override
    public Mono<Capacidad> registrarCapacidad(Capacidad capacidad) {
        return validateCapacidad(capacidad)
                .then(capacidadRepository.existsByName(capacidad.getNombre()))
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new CapacidadAlreadyExistsException("La capacidad con el nombre '" + capacidad.getNombre() + "' ya existe."));
                    }
                    return capacidadRepository.save(capacidad);
                });
    }

    @Override
    public Flux<Capacidad> listarCapacidades(String orderBy, boolean ascending, int page, int size) {
        if (orderBy.equals("nombre")) {
            return capacidadRepository.findAllSortedByName(ascending, page, size);
        } else if (orderBy.equals("tecnologias")) {
            return capacidadRepository.findAllSortedByTechnologiesCount(ascending, page, size);
        } else {
            return Flux.error(new InvalidCapacidadDataException("Criterio de ordenación no válido"));
        }
    }

    @Override
    public Mono<Capacidad> obtenerCapacidadPorId(Long id) {
        return capacidadRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Capacidad no encontrada")));
    }

    @Override
    public Mono<Void> eliminarCapacidad(Long id) {
        return capacidadRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Capacidad no encontrada")))
                .flatMap(capacidad -> capacidadRepository.deleteById(id));
    }
}
