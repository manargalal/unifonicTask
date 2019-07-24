package com.unifonic.assignment.models;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import lombok.Data;

@Data
public class HttpRequestDataModel{
private String url;
private HttpEntity httpEntity;
private List<NameValuePair> headers;
}
