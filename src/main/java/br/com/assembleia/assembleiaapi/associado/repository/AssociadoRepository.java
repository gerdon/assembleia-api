package br.com.assembleia.assembleiaapi.associado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.assembleiaapi.associado.model.Associado;

/**
 * @author Gerdon Mafra
 *
 */
public interface AssociadoRepository extends JpaRepository<Associado, Integer>{

}
