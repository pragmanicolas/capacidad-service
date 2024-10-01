package com.capacidad.service.adapters.out.client;

import com.capacidad.service.application.dto.TecnologiaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TecnologiaClient {

    private final WebClient webClient;

    public TecnologiaClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://tecnologia-service:8081/api/tecnologias").build();
    }

    public Flux<TecnologiaDTO> getAllTecnologias() {
        return webClient.get()
                .uri("/api/tecnologias")
                .retrieve()
                .bodyToFlux(TecnologiaDTO.class);
    }

    public Mono<TecnologiaDTO> getTecnologiaById(Long id) {
        return webClient.get()
                .uri("/api/tecnologias/{id}", id)
                .retrieve()
                .bodyToMono(TecnologiaDTO.class);
    }
}

