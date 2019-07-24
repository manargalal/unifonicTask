package com.unifonic.assignment.http.proxy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifonic.assignment.models.HttpResponseDataModel;

public class ResponseResolver {

	private ResponseResolver() {}

	public static HttpResponseDataModel resolve(HttpResponse httpResponse)throws  IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BufferedReader rd = null;
		if(httpResponse.getEntity()!=null){
			rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		}
		StringBuilder result = new StringBuilder();
		String line = "";
		if( rd!=null){
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		}
		return objectMapper.readValue(result.toString(), HttpResponseDataModel.class);
	}
}
