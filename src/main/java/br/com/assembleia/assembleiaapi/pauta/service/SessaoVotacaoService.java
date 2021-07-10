package br.com.assembleia.assembleiaapi.pauta.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.associado.service.AssociadoService;
import br.com.assembleia.assembleiaapi.pauta.exception.SessaoVotacaoException;
import br.com.assembleia.assembleiaapi.pauta.exception.UnableToVoteException;
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
	
	/**
	 * Registra o voto do associado na pauta
	 * 
	 * @param idAssociado
	 * @param idPauta
	 * @param voto
	 * @return Voto
	 * @throws SessaoVotacaoException
	 * @throws VotoRegistradoException
	 * @throws UnableToVoteException
	 */
	public Voto votar(Integer idAssociado, Integer idPauta, Boolean voto) 
			throws SessaoVotacaoException, VotoRegistradoException, UnableToVoteException {
		Associado associado = associadoService.findById(idAssociado);
		Voto votoRegistrado = new Voto();
		String cpfStatus = verificarAssociadoVotante(associado.getCpf());
		
		if(cpfStatus.equals("ABLE_TO_VOTE")) {
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
			
			votoRegistrado = votoService.save(novoVoto);
			
			if(votoRegistrado.getId() != null) {
				List<Voto> votos = sv.getVotos();
				votos.add(votoRegistrado);
				update(sv);
			}
		} else {
			throw new UnableToVoteException();
		}
		
		return votoRegistrado;
	}
	
	/**
	 * Verifica se a sessão de votação está ativa
	 * 
	 * @param sv
	 * @return Boolean
	 */
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
	
	/**
	 * Verifica se o associado está habilitado para registra o voto
	 * 
	 * @param cpf
	 * @return String
	 * @throws UnableToVoteException 
	 */
	public String verificarAssociadoVotante(String cpf) throws UnableToVoteException {
		 HttpResponse<JsonNode> jsonResponse = null;
		 JSONObject obj = null;

		 try {
		  jsonResponse = (HttpResponse<JsonNode>) Unirest.get("https://user-info.herokuapp.com/users/{cpf}")
			  .routeParam("cpf", cpf)
			  .asJson();
		  
		  obj = jsonResponse.getBody().getObject();
		 } catch (UnirestException e) {
			 e.printStackTrace();
		 }
		 
		if(!obj.has("status")) {
			throw new UnableToVoteException();
		}

		return obj.get("status").toString();
	}

}
