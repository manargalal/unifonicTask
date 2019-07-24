package com.unifonic.assignment.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadRequestException extends Exception{
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(HttpStatus code, String message) {
		super(message);
		this.code = code;
	}

	public BadRequestException(HttpStatus code, String message, Throwable cause) {
		super(message, cause);		
		this.code = code;
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	public BadRequestException(Throwable cause) {
		super(cause);
	}
}
