package br.com.assembleia.assembleiaapi.associado.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.associado.repository.AssociadoRepository;

/**
 * @author Gerdon Mafra
 *
 */

@Service
@Transactional
public class AssociadoService {
	
	@Autowired
	AssociadoRepository repository;
	
	public List<Associado> findAll() {
		return repository.findAll();
	}
	
	public Associado save(Associado entity) {		
		return repository.save(entity);
	}
	
	public Associado update(Associado entity) {
		return repository.save(entity); 
	}
	
	public Associado findById(Integer id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public void delete(Associado entity) {
		repository.delete(entity);
	}
	
}
