package mjv.sistemabiblioteca.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Resource
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
		String message = "";
		if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
			UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
			Class<? extends Throwable> exceptionClass = exception.getUndeclaredThrowable().getClass();
			return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
		} else {
			message = messageSource.getMessage("error.server", new Object[] { e.getMessage() }, null);
		}

		ResponseError error = new ResponseError();
		error.setError(message);
		error.setStatus("error");
		error.setTimestamp(LocalDateTime.now());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler({ BusinessException.class })
	private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
		ResponseError error = new ResponseError();
		error.setError(e.getMessage());
		error.setStatusCode(HttpStatus.CONFLICT.value());
		error.setStatus("error");
		error.setTimestamp(LocalDateTime.now());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    
	    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "erro de validação", errors);
	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
}