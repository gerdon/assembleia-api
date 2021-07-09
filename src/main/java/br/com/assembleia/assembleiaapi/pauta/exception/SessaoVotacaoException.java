package br.com.assembleia.assembleiaapi.pauta.exception;

/**
 * @author Gerdon Mafra
 *
 */
public class SessaoVotacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public String getMessage() {
        return "Está sessão de votação se encontra finalizada.";
    }

}
