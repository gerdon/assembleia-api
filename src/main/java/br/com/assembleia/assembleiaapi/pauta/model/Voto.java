package br.com.assembleia.assembleiaapi.pauta.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.assembleia.assembleiaapi.associado.model.Associado;
import br.com.assembleia.assembleiaapi.main.model.EntityMaster;

/**
 * @author Gerdon Mafra
 *
 */

@Entity
@Table(name = "voto", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_voto"))
public class Voto extends EntityMaster {

	private static final long serialVersionUID = 1L;
	
	private Boolean voto;
	
	@OneToOne
	@JoinColumn(name = "id_associado")
	private Associado associado;

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

}
