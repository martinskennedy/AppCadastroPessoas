package br.com.ksm.AppCadastroPessoas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Erro> handlerResourceNotFoundException(ResourceNotFoundException e){
				
		Erro erro = new Erro(e.getMessage(), "APP_CADASTRO_PESSOAS");
		
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
}
