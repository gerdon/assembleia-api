package br.com.assembleia.assembleiaapi.pauta.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.associado.service.AssociadoService;
import br.com.assembleia.assembleiaapi.pauta.exception.SessaoVotacaoException;
import br.com.assembleia.assembleiaapi.pauta.exception.VotoRegistradoException;
import br.com.assembleia.assembleiaapi.pauta.model.Pauta;
import br.com.assembleia.assembleiaapi.pauta.model.SessaoVotacao;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;
import br.com.assembleia.assembleiaapi.pauta.repository.SessaoVotacaoRepository;

/**
 * @author Gerdon Mafra
 *
 */

@Service
@Transactional
public class SessaoVotacaoService {
	
	@Autowired
	SessaoVotacaoRepository repository;
	
	@Autowired
	VotoService votoService;
	
	@Autowired
	AssociadoService associadoService;
	
	@Autowired
	PautaService pautaService;
	
	public List<SessaoVotacao> findAll() {
		return repository.findAll();
	}
	
	public SessaoVotacao save(SessaoVotacao entity) {		
		return repository.save(entity);
	}
	
	public SessaoVotacao update(SessaoVotacao entity) {
		return repository.save(entity); 
	}
	
	public SessaoVotacao findById(Integer id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public void delete(SessaoVotacao entity) {
		repository.delete(entity);
	}
	
	public Voto votar(Integer idAssociado, Integer idPauta, Boolean voto) throws SessaoVotacaoException, VotoRegistradoException {
		Associado associado = associadoService.findById(idAssociado);
		Pauta pauta = pautaService.findById(idPauta);
		SessaoVotacao sv = repository.findByPautaEquals(pauta);
		
		if(!verificarSessaoAtiva(sv)) {
			throw new SessaoVotacaoException();
		}
		
		Voto novoVoto = new Voto();
		novoVoto.setAssociado(associado);
		novoVoto.setVoto(voto);
		
		Boolean existeVoto = votoService.findByAssociadoAndSessao(associado.getId(), sv.getId());
		
		if(existeVoto) {
			throw new VotoRegistradoException();
		}
		
		Voto votoRegistrado = votoService.save(novoVoto);
		
		if(votoRegistrado.getId() != null) {
			List<Voto> votos = sv.getVotos();
			votos.add(votoRegistrado);
			update(sv);
		}
		
		return votoRegistrado;
	}
	
	public Boolean verificarSessaoAtiva(SessaoVotacao sv) {
		LocalDateTime dataAtual = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dataCriacao = LocalDateTime.parse(sv.getCreationDate().toString(), formatter);
		LocalDateTime dataEncerramento = dataCriacao;
		
		// Verifica se há horas informada para encerramento da sessão
		if(!sv.getTempoHoras().isBlank() || sv.getTempoHoras() != null) {			
			dataEncerramento = dataCriacao.plusHours(Long.parseLong(sv.getTempoHoras()));
		}
		
		// Verifica se há minutos informada para encerramento da sessão
		if(!sv.getTempoMinutos().isBlank() || sv.getTempoMinutos() != null) {			
			dataEncerramento = dataCriacao.plusMinutes(Long.parseLong(sv.getTempoMinutos()));
		}
		
		return dataAtual.isBefore(dataEncerramento);
	}

}
