package br.com.assembleia.assembleiaapi.pauta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.assembleia.assembleiaapi.pauta.model.Pauta;
import br.com.assembleia.assembleiaapi.pauta.service.PautaService;

/**
 * @author Gerdon Mafra
 *
 */

@RestController
@RequestMapping("pauta")
public class PautaController {
	
	@Autowired
	PautaService service;
	
	@GetMapping
	public ResponseEntity<List<Pauta>> findAll() {
		List<Pauta> entities = service.findAll();
		
		return new ResponseEntity<List<Pauta>>(entities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pauta> save(@Valid @RequestBody Pauta entity) {
		Pauta created = (Pauta) service.save(entity);
		
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<Pauta>(created, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pauta> update(@Valid @RequestBody Pauta entity) {
		Pauta updated = (Pauta) service.save(entity);
		
		return new ResponseEntity<Pauta>(updated, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pauta> getById(@PathVariable Integer id){
		Pauta entity = (Pauta) service.findById(id);
		
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		}
		
		return new ResponseEntity<Pauta>(entity, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pauta> deleteById(@PathVariable Integer id) {
		Pauta entity = (Pauta) service.findById(id);
		
		if(entity == null || entity.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		service.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
