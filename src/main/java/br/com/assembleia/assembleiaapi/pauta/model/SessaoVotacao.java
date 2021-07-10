package br.com.assembleia.assembleiaapi.pauta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.assembleia.assembleiaapi.main.model.EntityMaster;

/**
 * @author Gerdon Mafra
 *
 */

@Entity
@Table(name = "sessao_votacao", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_sessao_votacao"))
public class SessaoVotacao extends EntityMaster {

	private static final long serialVersionUID = 1L;
	
	private String tempoHoras = "0";
	
	private String tempoMinutos = "1";
	
	private Boolean ativa = true;
	
	@OneToOne
	@JoinColumn(name = "id_pauta")
	private Pauta pauta;
	
	@OneToMany
	@JoinColumn(name = "id_sessao_votacao")
	private List<Voto> votos = new ArrayList<Voto>();
	

	// TODO Adicionar essa lógica no Serviço da entidade
	/**
	 * Contabiliza o total de votos registrados na Pauta 
	 * @return String
	 */
	public String getVotosTotal() {
		Integer favoravel = 0;
		Integer desfavoravel = 0;
		
		for(Voto v : votos) {
			if (v.getVoto() == true) {
				favoravel++;
			} else {
				desfavoravel++;
			}
		}
		
		return "A pauta teve um total de "+ favoravel +" votos favoráveis e "
			+ desfavoravel +" votos desfavoráveis.";
	}

	public String getTempoHoras() {
		return tempoHoras;
	}

	public void setTempoHoras(String tempoHoras) {
		this.tempoHoras = tempoHoras;
	}

	public String getTempoMinutos() {
		return tempoMinutos;
	}

	// TODO Adicionar essa lógica no Serviço da entidade
	public void setTempoMinutos(String tempoMinutos) {
		if(tempoMinutos.equals("") || tempoMinutos == null || tempoMinutos.equals("0")) {
			this.tempoMinutos = "1";
		} else {			
			this.tempoMinutos = tempoMinutos;
		}
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

}
