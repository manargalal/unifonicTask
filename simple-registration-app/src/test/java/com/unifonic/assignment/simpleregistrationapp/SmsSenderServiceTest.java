package com.unifonic.assignment.simpleregistrationapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import com.unifonic.assignment.models.SendingDataRequestModel;
import com.unifonic.assignment.util.Constants;

public class SmsSenderServiceTest {

	@Mock
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Before
	public void init() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test                                                                                         
	public void sendSMS_from_Unifonic_API() {   
		String code = "1234";
		SendingDataRequestModel model =new SendingDataRequestModel();
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_APP_SID_KEY, Constants.UNIFONIC_APP_SID_VALUE));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_BODY_KEY, Constants.VERIFICATION_CODE_MESSAGE));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_RECEIPIECT_KEY, "966541111111"));
		urlParameters.add(new BasicNameValuePair(Constants.UNIFONIC_SENDER_ID_KEY, Constants.UNIFONIC_SENDER_ID_VALUE));

		Mockito.when(restTemplate.postForEntity(Constants.SEND_SMS_MESSAGES_API_URL, urlParameters,String.class))
		.thenReturn(new ResponseEntity(HttpStatus.OK));                                                    
	}


}
