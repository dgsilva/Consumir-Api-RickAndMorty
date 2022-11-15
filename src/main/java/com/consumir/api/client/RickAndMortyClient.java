package com.consumir.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.consumir.api.response.CharacterResponse;
import com.consumir.api.response.EpisodioResponse;
import com.consumir.api.response.ListOfEpisodeResponse;
import com.consumir.api.response.LocationResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

	private final WebClient webClient;

	public RickAndMortyClient(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
	}
	
	public Mono<CharacterResponse> findAndCharacterById(String id){	
		log.info("buscando o personagem com o id [{}]", id);
		return webClient
				.get()
				.uri("/character/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Verifique os parametros no formatos")))
				.bodyToMono(CharacterResponse.class);
				
	}
	
	public Mono<LocationResponse> findAndLocationById(String id){	
		log.info("buscando a localização com o id [{}]", id);
		return webClient
				.get()
				.uri("/location/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Verifique os parametros no formatos")))
				.bodyToMono(LocationResponse.class);
				
	}
	
	public Mono<EpisodioResponse> findAndEpisodioById(String id){	
		log.info("buscando o Episodio com o id [{}]", id);
		return webClient
				.get()
				.uri("/episode/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Verifique os parametros no formatos")))
				.bodyToMono(EpisodioResponse.class);
				
	}
	
	public Flux<ListOfEpisodeResponse> getAllEpisode(){	
		log.info("Listando todos os episódios");
		return webClient
				.get()
				.uri("/episode/")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Verifique os parametros no formatos")))
				.bodyToFlux(ListOfEpisodeResponse.class);
				
	}
}
