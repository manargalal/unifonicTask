package com.unifonic.assignment.http.proxy.client;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import com.unifonic.assignment.util.Constants;

import lombok.Data;

@Data
public class Request {
	private HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase;

	private Request(RequestBuilder requestBuilder) {
		this.httpEntityEnclosingRequestBase=requestBuilder.httpEntityEnclosingRequestBase;
	}
	public static class RequestBuilder {
		private HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase;

		public RequestBuilder body(HttpEntity httpEntity) {
			this.httpEntityEnclosingRequestBase.setEntity(httpEntity);
			return this;
		}
		public RequestBuilder header(List<NameValuePair> headers) {
			headers.forEach(
					header -> this.httpEntityEnclosingRequestBase.setHeader(header.getName(), header.getValue()));
			return this;
		}
		public RequestBuilder post(String url) {
			this.httpEntityEnclosingRequestBase=RequestFactory.create(Constants.HTTP_POST_METHOD).instance(url);
			return this;
		}
		public RequestBuilder get(String url) {
			this.httpEntityEnclosingRequestBase=RequestFactory.create(Constants.HTTP_GET_METHOD).instance(url);
			return this;
		}
		public Request build() {
			return new Request(this);
		}
		
	}

}
