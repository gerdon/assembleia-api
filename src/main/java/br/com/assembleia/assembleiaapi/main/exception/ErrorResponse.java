package br.com.assembleia.assembleiaapi.main.exception;

import java.util.List;

/**
 * @author Gerdon Mafra
 *
 */
public class ErrorResponse {

	private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final String user;
    private final List<ErrorObject> errors;
    
    public ErrorResponse(String message, int code, String status, String objectName, String user, List<ErrorObject> errors) {
		super();
		this.message = message;
		this.code = code;
		this.status = status;
		this.objectName = objectName;
		this.user = user;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getObjectName() {
		return objectName;
	}

	public String getUser() {
		return user;
	}

	public List<ErrorObject> getErrors() {
		return errors;
	}
}
