package br.com.assembleia.assembleiaapi.pauta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.assembleiaapi.pauta.model.Pauta;
import br.com.assembleia.assembleiaapi.pauta.repository.PautaRepository;

/**
 * @author Gerdon Mafra
 *
 */

@Service
@Transactional
public class PautaService {

	@Autowired
	PautaRepository repository;
	
	public List<Pauta> findAll() {
		return repository.findAll();
	}
	
	public Pauta save(Pauta entity) {		
		return repository.save(entity);
	}
	
	public Pauta update(Pauta entity) {
		return repository.save(entity); 
	}
	
	public Pauta findById(Integer id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public void delete(Pauta entity) {
		repository.delete(entity);
	}
	
}
