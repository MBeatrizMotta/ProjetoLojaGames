package com.mundodosgames.mundodosgames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mundodosgames.mundodosgames.model.GamesModel;
import com.mundodosgames.mundodosgames.repository.GamesRepository;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GamesController {

	@Autowired
	public GamesRepository gamesRepository;
	
	@GetMapping
	public ResponseEntity<List<GamesModel>> GetAll(){
		return ResponseEntity.ok(gamesRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GamesModel> getById(@PathVariable Long id){
		return gamesRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<GamesModel>> getByTitle(@PathVariable String nome){
		return ResponseEntity.ok(gamesRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<GamesModel> post(@Valid @RequestBody GamesModel games){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(gamesRepository.save(games));
	}
	

	@PutMapping
	public ResponseEntity<GamesModel> put(@Valid @RequestBody GamesModel games){
		return gamesRepository.findById(games.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED)
				.body(gamesRepository.save(games)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<GamesModel> games = gamesRepository.findById(id);
		
		if(games.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		gamesRepository.deleteById(id);
	}
}

