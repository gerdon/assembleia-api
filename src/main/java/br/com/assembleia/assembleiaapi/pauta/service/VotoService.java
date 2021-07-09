package br.com.assembleia.assembleiaapi.pauta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;
import br.com.assembleia.assembleiaapi.pauta.repository.VotoRepository;

/**
 * @author Gerdon Mafra
 *
 */

@Service
@Transactional
public class VotoService {

	@Autowired
	VotoRepository repository;
	
	public Voto save(Voto entity) {		
		return repository.save(entity);
	}
	
	public Voto findByAssociadoEquals(Associado associado) {
		return repository.findByAssociadoEquals(associado);
	}
	
	public Voto findByAssociadoAndSessao(Integer idAssociado, Integer idSessao) {
		return repository.findByAssociadoAndSessao(idAssociado, idSessao);
	}
}
