package br.com.assembleia.assembleiaapi.pauta.exception;

/**
 * @author Gerdon Mafra
 *
 */
public class UnableToVoteException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public String getMessage() {
        return "Associado não habilitado para votar nesta Pauta";
    }

}
