package com.unifonic.assignment.exception;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.unifonic.assignment.exception.model.Error;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.unifonic.assignment.exception.model.Defect;



@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserRegistartionExceptionHandler {


	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Defect> handleUserRegistrationException(Exception thr) {
		HttpStatus status = (thr instanceof BadRequestException) ? ((((BadRequestException) thr).getCode() != null)
				? ((BadRequestException) thr).getCode()
						: HttpStatus.INTERNAL_SERVER_ERROR) : HttpStatus.INTERNAL_SERVER_ERROR;
				return new ResponseEntity<>(buildError(thr,status),status);
	}

	

	private Defect buildError(Throwable thr, HttpStatus status) {
		Defect error = new Defect();
		error.setStatus(status.value());
		error.setError(status.name());
		do {
			error.setMessage(thr.getMessage());
			thr = thr.getCause();
		} while (thr != null);

		return error;
	}

}
