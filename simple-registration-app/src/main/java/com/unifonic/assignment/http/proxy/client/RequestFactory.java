package com.unifonic.assignment.http.proxy.client;

import com.unifonic.assignment.util.Constants;

public class RequestFactory {
	private RequestFactory() {
	}

	public static IRequest create(String method) {
		if (method.equalsIgnoreCase(Constants.HTTP_POST_METHOD)) {
			return new HttpPostRequest();
		}
		throw new IllegalArgumentException("No such type");
	}

}
