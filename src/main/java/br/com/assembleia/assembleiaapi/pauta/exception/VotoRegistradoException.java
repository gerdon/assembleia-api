package br.com.assembleia.assembleiaapi.pauta.exception;

/**
 * @author Gerdon Mafra
 *
 */
public class VotoRegistradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public String getMessage() {
        return "Voto já registrado! Não é possível votar novamente.";
    }

}
