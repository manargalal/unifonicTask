package com.unifonic.assignment.models;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
import lombok.ToString;

@Data
@ToString()
public class HttpResponseDataModel {
private String success;
private JsonNode message;
private String errorCode;
private JsonNode data;

}
