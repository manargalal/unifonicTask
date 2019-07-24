package com.unifonic.assignment.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifonic.assignment.http.proxy.client.ResponseResolver;
import com.unifonic.assignment.models.HttpRequestDataModel;
import com.unifonic.assignment.models.HttpResponseDataModel;
import com.unifonic.assignment.models.SendingDataRequestModel;
import com.unifonic.assignment.sms.gateway.service.SmsServiceInvoker;
import com.unifonic.assignment.util.Constants;
@Service
public class SmsSenderServiceImpl implements SenderService {

	@Autowired
	private SmsServiceInvoker smsServiceInvoker;
	
	

	@Override
	public HttpResponseDataModel send(SendingDataRequestModel sendingDataRequestModel) throws IOException {
		List<NameValuePair> headers = new ArrayList<>();
		List<NameValuePair> urlParameters = new ArrayList<>();
		
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_APP_SID_KEY, Constants.UNIFONIC_APP_SID_VALUE));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_BODY_KEY, sendingDataRequestModel.getMessage()));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_RECEIPIECT_KEY, sendingDataRequestModel.getRecipient()));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_SENDER_ID_KEY, Constants.UNIFONIC_SENDER_ID_VALUE));

		HttpRequestDataModel requestModel = new HttpRequestDataModel();
		requestModel.setUrl(Constants.SEND_SMS_MESSAGES_API_URL);
		requestModel.setHeaders(headers);
		requestModel.setHttpEntity(new UrlEncodedFormEntity(urlParameters));
		
		return ResponseResolver.resolve(smsServiceInvoker.executeRequest(requestModel));

	}
}
