package br.com.assembleia.assembleiaapi.pauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.assembleiaapi.pauta.model.Pauta;
import br.com.assembleia.assembleiaapi.pauta.model.SessaoVotacao;

/**
 * @author Gerdon Mafra
 *
 */
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {

	SessaoVotacao findByPautaEquals(Pauta Pauta);
}
