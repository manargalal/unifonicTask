package com.unifonic.assignment.http.proxy.client;

import org.apache.http.client.methods.HttpPost;

public class HttpPostRequest implements IRequest{

	@Override
	public HttpPost instance(String url) {
		return new HttpPost(url);
	}

}
