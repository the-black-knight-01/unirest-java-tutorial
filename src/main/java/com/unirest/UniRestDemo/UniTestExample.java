package com.unirest.UniRestDemo;

import java.io.File;

import org.apache.http.HttpHost;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

/**
 * Hello world!
 *
 */
public class UniTestExample 
{
    private static final String String = null;

	public static void main( String[] args ) throws UnirestException
    {
    	/* 1. Getting response using Unirest 
        final HttpResponse<String> response = Unirest.get("http://httpbin.org/get").asString();
    	System.out.println( response.getBody() );
    	*/
    	
    	/* 2. Adding query parameter
        final HttpResponse<String> response = Unirest.get("http://httpbin.org/get").queryString("limit", 10).asString();
    	System.out.println( response.getBody() );
    	 */
    	
    	/* 3. Accessing Response JSON elements 
        final HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/get").queryString("limit", 10).asJson();
    	System.out.println("Server IP Addess: "+ response.getBody().getObject().getString("origin"));
    	*/
    	 
    	/* Setting Custom Header */
      //  Unirest.setDefaultHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.3611");	
      //  Unirest.setDefaultHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.81");
     //   Unirest.setDefaultHeader("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.81");
    	/*Setting Proxies   https://free-proxy-list.net/ 
    	Unirest.setProxy(new HttpHost("193.86.114.42",21231));
    	
        final HttpResponse<JsonNode> response = Unirest.get("http://httpbin.org/get").queryString("limit", 10).asJson();
    	System.out.println("Server IP Addess: "+ response.getBody().getObject().getString("origin"));
    	System.out.println("User-Agent "+ response.getBody().getObject().getJSONObject("headers").getString("User-Agent"));
    	System.out.println( response.getBody());

    	*/
        /*
        final HttpResponse<String> response = Unirest.post("http://httpbin.org/post").asString();
        // POST Request
    	HttpResponse<String> jsonResponse = Unirest.post("http://httpbin.org/post")
    			  .header("accept", "application/json")
    			  .queryString("apiKey", "123")
    			  .field("parameter", "value")
    			  .field("foo", "bar")
    			  .asString();
       // Route Parameters
       HttpResponse<String> jsonResponse1 = Unirest.get("http://httpbin.org/{method}")
        .routeParam("method", "get")
        .queryString("name", "Mark")
        .asString();
       System.out.println( jsonResponse1.getBody());
       */
    	/*
       // File upload 
        HttpResponse<String> jsonResponse = Unirest.post("http://httpbin.org/post")
        		  .header("accept", "application/json")
        		  .field("parameter", "value")
        		  .field("file", new File("/Users/admin/Documents/switch.cpp"))
        		  .asString();
        System.out.println( jsonResponse.getBody());  
        */
    	
    	/*
      // Custom Entity Body
        HttpResponse<String> jsonResponse = Unirest.post("http://httpbin.org/post")
        		  .header("accept", "application/json")
        		  .body("{\"parameter\":\"value\", \"foo\":\"bar\"}")
        		  .asString();
        System.out.println(jsonResponse.getBody());  
    */
    	/*
       //Basic Authentication 
    	HttpResponse<String> response = Unirest.get("http://httpbin.org/get")
	    			.basicAuth("username", "password")
	    			.asString();
        System.out.println(response.getBody());  
    */
        
    	HttpResponse<java.lang.String> request = Unirest.get("http://httpbin.org/get").asString();
    //	GetRequest request = Unirest.head(String url);
    //	HttpRequestWithBody request = Unirest.post(String url);
    //	HttpRequestWithBody request = Unirest.put(String url);
    //	HttpRequestWithBody request = Unirest.patch(String url);
    //	HttpRequestWithBody request = Unirest.options(String url);
    //	HttpRequestWithBody request = Unirest.delete(String url);
        System.out.println(request.getBody());  

    }
}
