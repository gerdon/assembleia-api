package br.com.assembleia.assembleiaapi.pauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;

/**
 * @author Gerdon Mafra
 *
 */
public interface VotoRepository extends JpaRepository<Voto, Integer> {

	Voto findByAssociadoEquals(Associado associado);
	
//	@Query("SELECT v FROM Voto v "
//			+"INNER JOIN SessaoVotacao sv ON sv.id = ?2 "
//			+"WHERE v.associado = ?1")
//	Voto findByAssociadoAndSessao(Integer idAssociado, Integer idSessao);
	
	@Query(value = "SELECT * FROM Voto v WHERE v.id_associado = ?1 AND v.id_sessao_votacao = ?2", nativeQuery = true)
	Voto findByAssociadoAndSessao(Integer idAssociado, Integer idSessao);
	
}
