package com.unifonic.assignment.models;

import lombok.Data;

@Data
public class SendingDataRequestModel {
	private String recipient;
	private String message;
}
