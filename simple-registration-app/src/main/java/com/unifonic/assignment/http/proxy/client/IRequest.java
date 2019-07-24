package com.unifonic.assignment.http.proxy.client;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public interface IRequest {
public HttpEntityEnclosingRequestBase  instance(String url);
}
