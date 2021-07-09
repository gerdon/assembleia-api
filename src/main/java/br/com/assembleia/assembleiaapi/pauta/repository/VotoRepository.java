package br.com.assembleia.assembleiaapi.pauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;

/**
 * @author Gerdon Mafra
 *
 */
public interface VotoRepository extends JpaRepository<Voto, Integer> {

	Voto findByAssociadoEquals(Associado associado);
		
	@Query(value = "SELECT case when count(v.*) > 0 then true else false end FROM pauta.voto v "
			+"WHERE v.id_associado = :idAssociado AND v.id_sessao_votacao = :idSessao", nativeQuery = true)
	Boolean findByAssociadoAndSessao(@Param("idAssociado") Integer idAssociado, @Param("idSessao") Integer idSessao);
	
}
