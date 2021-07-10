package br.com.assembleia.assembleiaapi.pauta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.assembleia.assembleiaapi.pauta.exception.SessaoVotacaoException;
import br.com.assembleia.assembleiaapi.pauta.exception.UnableToVoteException;
import br.com.assembleia.assembleiaapi.pauta.exception.VotoRegistradoException;
import br.com.assembleia.assembleiaapi.pauta.model.SessaoVotacao;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;
import br.com.assembleia.assembleiaapi.pauta.service.SessaoVotacaoService;
import io.swagger.annotations.Api;

/**
 * @author Gerdon Mafra
 *
 */

@RestController
@RequestMapping("sessao-votacao")
@Api(value = "sessao-votacao")
@CrossOrigin(origins = "*")
public class SessaoVotacaoController {
	
	@Autowired
	SessaoVotacaoService service;
	
	@GetMapping
	public ResponseEntity<List<SessaoVotacao>> findAll() {
		List<SessaoVotacao> entities = service.findAll();
		
		return new ResponseEntity<List<SessaoVotacao>>(entities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SessaoVotacao> save(@Valid @RequestBody SessaoVotacao entity) {
		SessaoVotacao created = (SessaoVotacao) service.save(entity);
		
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<SessaoVotacao>(created, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<SessaoVotacao> update(@Valid @RequestBody SessaoVotacao entity) {
		SessaoVotacao updated = (SessaoVotacao) service.save(entity);
		
		return new ResponseEntity<SessaoVotacao>(updated, HttpStatus.OK);
	}
	
	/**
	 * Votar
	 * 
	 * @return Voto
	 * @throws VotoRegistradoException 
	 * @throws SessaoVotacaoException 
	 * @throws UnableToVoteException 
	 */
	@GetMapping("v1/votar")
	public ResponseEntity<Voto> votar(@RequestParam Integer idAssociado, @RequestParam Integer idPauta, @RequestParam Boolean voto) 
			throws SessaoVotacaoException, VotoRegistradoException, UnableToVoteException {
		Voto v = service.votar(idAssociado, idPauta, voto);
		
		return new ResponseEntity<Voto>(v, HttpStatus.OK);
	}

}
