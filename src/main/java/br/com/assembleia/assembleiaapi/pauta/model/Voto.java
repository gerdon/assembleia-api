package br.com.assembleia.assembleiaapi.pauta.model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(associado, voto);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Voto))
			return false;
		Voto other = (Voto) obj;
		return Objects.equals(associado, other.associado) && Objects.equals(voto, other.voto);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Voto [voto=");
		builder.append(voto);
		builder.append(", associado=");
		builder.append(associado);
		builder.append(", id=");
		builder.append(id);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append("]");
		return builder.toString();
	}

}
