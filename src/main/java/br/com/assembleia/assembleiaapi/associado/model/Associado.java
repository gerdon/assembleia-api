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
	
	@Size(min = 11)
	@Column(length = 11, nullable = false)
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
