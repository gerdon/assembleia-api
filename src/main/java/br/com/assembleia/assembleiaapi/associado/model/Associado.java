package br.com.assembleia.assembleiaapi.associado.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.assembleia.assembleiaapi.main.model.EntityMaster;

/**
 * @author Gerdon Mafra
 *
 */

@Entity
@Table(name = "associado", schema = "associado")
@AttributeOverride(name = "id", column = @Column(name = "id_associado"))
public class Associado extends EntityMaster {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 3, max = 120)
	@Column(length = 120, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
