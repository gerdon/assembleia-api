package br.com.assembleia.assembleiaapi.pauta.model;

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
@Table(name = "pauta", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_pauta"))
public class Pauta extends EntityMaster {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 10, max = 240)
	@Column(length = 240, nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
