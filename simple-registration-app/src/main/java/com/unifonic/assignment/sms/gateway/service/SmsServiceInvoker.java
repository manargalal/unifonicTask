package com.unifonic.assignment.sms.gateway.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifonic.assignment.models.HttpRequestDataModel;

@Service
public class SmsServiceInvoker {
	
	private SmsHandlerService smsHandlerService;

	public SmsServiceInvoker(SmsHandlerService smsHandlerService) {
		this.smsHandlerService = smsHandlerService;
	}

	public HttpResponse executeRequest(HttpRequestDataModel requestModel) throws IOException {
		return smsHandlerService.execute(requestModel);
	}
}
