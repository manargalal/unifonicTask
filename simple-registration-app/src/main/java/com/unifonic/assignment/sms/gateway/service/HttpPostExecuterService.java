package com.unifonic.assignment.sms.gateway.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.unifonic.assignment.http.proxy.client.Request;
import com.unifonic.assignment.models.HttpRequestDataModel;

@Service	
public class HttpPostExecuterService implements SmsHandlerService{

	@Override
	public HttpResponse execute(HttpRequestDataModel requestModel) throws  IOException {
		Request request = new Request.RequestBuilder().post(requestModel.getUrl()).body(requestModel.getHttpEntity()).build();
		return SmsHandlerService.super.init().execute(request.getHttpEntityEnclosingRequestBase());
	}

}
