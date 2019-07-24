package com.unifonic.assignment.sms.gateway.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import com.unifonic.assignment.models.HttpRequestDataModel;
@Service
public interface SmsHandlerService {

	default HttpClient init() {
		return HttpClientBuilder.create().build();
	}
	public HttpResponse execute(HttpRequestDataModel requestModel) throws IOException;
}
