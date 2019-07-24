package com.unifonic.assignment.exception.model;

import java.time.LocalDateTime;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Defect {

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'")
	private LocalDateTime timestamp = LocalDateTime.now();

	private Integer status;

	private String error;

	private String message;
	
}

