package com.unifonic.assignment.service;

import java.io.IOException;

import com.unifonic.assignment.models.SendingDataRequestModel;

public interface SenderService {
	public Object send(SendingDataRequestModel sendingDataRequestModel) throws IOException;

}
