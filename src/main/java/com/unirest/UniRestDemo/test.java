package com.unirest.UniRestDemo;

import java.io.File;

import org.apache.http.HttpHost;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

public class test {
	public static void main( String[] args ) throws UnirestException
    {
		final HttpResponse<String> response = Unirest.get("http://httpbin.org/get").asString();
    	System.out.println( response.getBody() );
    }

}
