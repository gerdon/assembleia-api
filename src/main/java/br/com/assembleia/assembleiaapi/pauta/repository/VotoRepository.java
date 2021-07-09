package br.com.assembleia.assembleiaapi.pauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.pauta.model.Voto;

/**
 * @author Gerdon Mafra
 *
 */
public interface VotoRepository extends JpaRepository<Voto, Integer> {

	Voto findByAssociadoEquals(Associado associado);
}
