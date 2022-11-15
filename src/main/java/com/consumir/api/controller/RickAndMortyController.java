package com.consumir.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumir.api.client.RickAndMortyClient;
import com.consumir.api.response.CharacterResponse;
import com.consumir.api.response.EpisodioResponse;
import com.consumir.api.response.ListOfEpisodeResponse;
import com.consumir.api.response.LocationResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

	@Autowired
	RickAndMortyClient rickClient;
	
	@GetMapping("characters/{id}")
	public Mono<CharacterResponse>getCharacterById(@PathVariable String id){
		return rickClient.findAndCharacterById(id);
	}
	
	@GetMapping("locations/{id}")
	public Mono<LocationResponse>getLocationById(@PathVariable String id){
		return rickClient.findAndLocationById(id);
	}
	
	@GetMapping("episode/{id}")
	public Mono<EpisodioResponse>getEpisodioById(@PathVariable String id){
		return rickClient.findAndEpisodioById(id);
	}
	
	@GetMapping("/episodes")
	public Flux<ListOfEpisodeResponse>getAll(){
		return rickClient.getAllEpisode();
	}
}
