package com.bridgelabz.fundoonotes.exception;

import java.util.Locale;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.bridgelabz.fundoonotes.util.Response;
import com.bridgelabz.fundoonotes.util.ErrorResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ResponseStatus
@Data
@Slf4j
public class NotesException extends RuntimeException {

	public NotesException(int statusCode, String statusmessage) {
		super(statusmessage);
		StatusCode = statusCode;
		Statusmessage = statusmessage;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int StatusCode;
	private String Statusmessage;
	
	/**
	 * 
	 * @return error response
	 */
	public Response getErrorResponse() {
		log.error("Error msg sttaus:" + getStatusmessage());
		return getErrorResponse(Locale.getDefault());
	}
	
	/**
	 * 
	 * @param locale : locale
	 * @return : Response type
	 */
	public Response getErrorResponse(Locale locale) {
		log.error("Error msg notes:" + getStatusmessage());
		ErrorResponse err = new ErrorResponse(StatusCode, Statusmessage, getStackTrace());
		err.setStatusCode(getStatusCode());
		err.setStatusmessage(getStatusmessage());
		return err;
	

}}
