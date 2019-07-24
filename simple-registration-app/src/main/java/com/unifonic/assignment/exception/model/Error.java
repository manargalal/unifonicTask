package com.unifonic.assignment.exception.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
	private HttpStatus status;
     private final String message;
     private List<FieldError> fieldErrors = new ArrayList<>();
    public  Error(HttpStatus status, String message) {
         this.status = status;
         this.message = message;
     }
    public void addFieldError(String path, String message) {
        FieldError error = new FieldError(path, message, message);
        fieldErrors.add(error);
    }

   }
