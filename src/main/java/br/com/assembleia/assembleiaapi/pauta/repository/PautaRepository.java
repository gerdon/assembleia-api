package br.com.assembleia.assembleiaapi.pauta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.assembleiaapi.pauta.model.Pauta;

/**
 * @author Gerdon Mafra
 *
 */
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
