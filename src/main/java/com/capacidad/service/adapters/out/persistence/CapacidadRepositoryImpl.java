package com.capacidad.service.adapters.out.persistence;

import com.capacidad.service.application.port.out.CapacidadRepository;
import com.capacidad.service.domain.Capacidad;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CapacidadRepositoryImpl implements CapacidadRepository {

    private final SpringDataCapacidadRepository springDataCapacidadRepository;

    public CapacidadRepositoryImpl(SpringDataCapacidadRepository springDataCapacidadRepository) {
        this.springDataCapacidadRepository = springDataCapacidadRepository;
    }

    @Override
    public Mono<Capacidad> save(Capacidad capacidad) {
        CapacidadEntity entity = new CapacidadEntity(
                capacidad.getId(),
                capacidad.getNombre(),
                capacidad.getDescripcion(),
                capacidad.getTecnologiaIds()
        );

        return springDataCapacidadRepository.save(entity)
                .map(savedEntity -> new Capacidad(
                        savedEntity.getId(),
                        savedEntity.getNombre(),
                        savedEntity.getDescripcion(),
                        savedEntity.getTecnologiaIds()
                ));
    }

    @Override
    public Mono<Capacidad> findById(Long id) {
        return springDataCapacidadRepository.findById(id)
                .map(entity -> new Capacidad(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getTecnologiaIds()
                ));
    }

    @Override
    public Flux<Capacidad> findAll() {
        return springDataCapacidadRepository.findAll()
                .map(entity -> new Capacidad(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getTecnologiaIds()
                ));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return springDataCapacidadRepository.deleteById(id);
    }

    @Override
    public Flux<Capacidad> findAllSortedByName(boolean ascending, int page, int size) {
        return springDataCapacidadRepository.findAll()
                .sort((c1, c2) -> ascending ? c1.getNombre().compareToIgnoreCase(c2.getNombre()) : c2.getNombre().compareToIgnoreCase(c1.getNombre()))
                .skip((long) page * size)
                .take(size)
                .map(entity -> new Capacidad(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getTecnologiaIds()
                ));
    }

    @Override
    public Flux<Capacidad> findAllSortedByTechnologiesCount(boolean ascending, int page, int size) {
        return springDataCapacidadRepository.findAll()
                .sort((c1, c2) -> ascending ? Integer.compare(c1.getTecnologiaIds().size(), c2.getTecnologiaIds().size())
                        : Integer.compare(c2.getTecnologiaIds().size(), c1.getTecnologiaIds().size()))
                .skip((long) page * size)
                .take(size)
                .map(entity -> new Capacidad(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getDescripcion(),
                        entity.getTecnologiaIds()
                ));
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return springDataCapacidadRepository.existsByNombre(name);
    }
}
