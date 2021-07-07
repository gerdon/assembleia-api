package br.com.assembleia.assembleiaapi.associado.controller;

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

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.associado.service.AssociadoService;

/**
 * @author Gerdon Mafra
 *
 */

@RestController
@RequestMapping("associado")
public class AssociadoController {
	
	@Autowired
	AssociadoService service;
	
	@GetMapping
	public ResponseEntity<List<Associado>> findAll() {
		List<Associado> entities = service.findAll();
		
		return new ResponseEntity<List<Associado>>(entities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Associado> save(@Valid @RequestBody Associado entity) {
		Associado created = (Associado) service.save(entity);
		
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<Associado>(created, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Associado> update(@Valid @RequestBody Associado entity) {
		Associado updated = (Associado) service.save(entity);
		
		return new ResponseEntity<Associado>(updated, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Associado> getById(@PathVariable Integer id){
		Associado entity = (Associado) service.findById(id);
		
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		}
		
		return new ResponseEntity<Associado>(entity, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Associado> deleteById(@PathVariable Integer id) {
		Associado entity = (Associado) service.findById(id);
		
		if(entity == null || entity.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		service.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
