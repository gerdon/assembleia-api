package br.com.assembleia.assembleiaapi.main.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Gerdon Mafra
 *
 * Trata exceções das requisições anotadas com @Valid validando os parametros 
 * das entidades anotadas com Constraints Bean Validation
 * 
 */

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, request, errors);

        return new ResponseEntity<>(errorResponse, status);
    }

	private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {

	    return ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	            .collect(Collectors.toList());
	}

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, 
    		HttpStatus status, WebRequest request, List<ErrorObject> errors) {

        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), request.getRemoteUser(), errors);
    }
}
