package com.unifonic.assignment.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;



@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationException extends Exception {
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	public UserRegistrationException(String message) {
		super(message);
	}

	public UserRegistrationException(HttpStatus code, String message) {
		super(message);
		this.code = code;
	}

	public UserRegistrationException(HttpStatus code, String message, Throwable cause) {
		super(message, cause);		
		this.code = code;
	}

	public UserRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}
	public UserRegistrationException(Throwable cause) {
		super(cause);
	}
}
