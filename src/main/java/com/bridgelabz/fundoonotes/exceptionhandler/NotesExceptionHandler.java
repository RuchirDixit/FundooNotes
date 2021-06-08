package com.bridgelabz.fundoonotes.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.util.Response;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class NotesExceptionHandler {
	/**
	 * Exception handler method to handle custom NotesException
	 * @param e : Exception
	 * @return : ResponseEntity<Response>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception e){
		log.error(e.getMessage(), e);
		NotesException he = new NotesException(100, e.getMessage());
		return new ResponseEntity<>(he.getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
