package com.unirest.UniRestDemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;


public class HttpClient implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(HttpClient.class.getName());
    private String user;
    private String password;

    /**
     * Create a http client with no auth (user anonymous).
     */
    public HttpClient() {
        init();
    }

    /**
     * Create a http client with basic auth.
     *
     * @param user
     * @param passwd
     */
    public HttpClient(String user, String passwd) {
        init();
        this.user = user;
        this.password = passwd;
    }

    /**
     * Initialize the instance.
     */
    private void init() {
        // setup to serialize Json from\to Object using jackson object mapper
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public <T> Response<T> get(String url, Class<T> clazz) throws HttpClientException {
        return get(url, null, null, null, clazz);
    }

    public <T> Response<T> get(String url,
                      Map<String, String> routeParamsMap,
                      Map<String, String> queryStringMap,
                      Map<String, String> headersMap,
                      Class<T> clazz) throws HttpClientException {
        GetRequest request = Unirest.get(url);
        return executeGetRequest(routeParamsMap, queryStringMap, headersMap, clazz, request);
    }

    public <T> Response<T> head(String url, Class<T> clazz) throws HttpClientException {
        return head(url, null, null, null, clazz);
    }

    public <T> Response<T> head(String url,
                     Map<String, String> routeParamsMap,
                     Map<String, String> queryStringMap,
                     Map<String, String> headersMap,
                     Class<T> clazz) throws HttpClientException {
        GetRequest request = Unirest.head(url);
        return executeGetRequest(routeParamsMap, queryStringMap, headersMap, clazz, request);
    }

    public Response<Object> post(String url, Object bodyObject) throws HttpClientException {
        return post(url, null, null, null, bodyObject);
    }

    public Response<Object> post(String url,
                     Map<String, String> routeParamsMap,
                     Map<String, String> queryStringMap,
                     Map<String, String> headersMap,
                     Object bodyObject) throws HttpClientException {
        HttpRequestWithBody request = Unirest.post(url);
        return executeHttpRequestWithBody(routeParamsMap, queryStringMap, headersMap, bodyObject, request);
    }

    public Response<Object> put(String url, Object bodyObject) throws HttpClientException {
        return put(url, null, null, null, bodyObject);
    }

    public Response<Object> put(String url,
                      Map<String, String> routeParamsMap,
                      Map<String, String> queryStringMap,
                      Map<String, String> headersMap,
                      Object bodyObject) throws HttpClientException {
        HttpRequestWithBody request = Unirest.put(url);
        return executeHttpRequestWithBody(routeParamsMap, queryStringMap, headersMap, bodyObject, request);
    }

    public Response<Object> delete(String url, Object bodyObject) throws HttpClientException {
        return delete(url, null, null, null, bodyObject);
    }

    public Response<Object> delete(String url,
                         Map<String, String> routeParamsMap,
                         Map<String, String> queryStringMap,
                         Map<String, String> headersMap,
                         Object bodyObject) throws HttpClientException {
        HttpRequestWithBody request = Unirest.delete(url);
        return executeHttpRequestWithBody(routeParamsMap, queryStringMap, headersMap, bodyObject, request);

    }


    private <T> Response<T> executeGetRequest(Map<String, String> routeParamsMap, Map<String, String> queryStringMap, Map<String, String> headersMap, Class<T> clazz, GetRequest request) throws HttpClientException {
        request.basicAuth(user, password);
        if(routeParamsMap != null) {
            for (Map.Entry<String, String> entry : routeParamsMap.entrySet()) {
                request.routeParam(entry.getKey(), entry.getValue());
            }
        }
        if (queryStringMap != null) {
            for (Map.Entry<String, String> entry : queryStringMap.entrySet()) {
                request.queryString(entry.getKey(), entry.getValue());
            }
        }
        if (headersMap != null) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                request.header(entry.getKey(), entry.getValue());
            }
        }
        try {
            HttpResponse<T> response = request.asObject(clazz);
            Response<T> r = new Response<T>();
            r.setStatusCode(response.getStatus());
            r.setStatusText(response.getStatusText());
            r.setRawBody(getStringFromInputStream(response.getRawBody()));
            r.setParsedObject(response.getBody());
            return r;
        } catch (UnirestException e) {
            throw new HttpClientException(e);
        }
    }


    private Response<Object> executeHttpRequestWithBody(Map<String, String> routeParamsMap, Map<String, String> queryStringMap, Map<String, String> headersMap, Object bodyObject, HttpRequestWithBody request) throws HttpClientException {
        request.basicAuth(user, password);
        if(routeParamsMap != null) {
            for (Map.Entry<String, String> entry : routeParamsMap.entrySet()) {
                request.routeParam(entry.getKey(), entry.getValue());
            }
        }
        if (queryStringMap != null) {
            for (Map.Entry<String, String> entry : queryStringMap.entrySet()) {
                request.queryString(entry.getKey(), entry.getValue());
            }
        }
        if (headersMap != null) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                request.header(entry.getKey(), entry.getValue());
            }
        }
        if(bodyObject != null) {
            request.body(bodyObject);
        }
        try {
            HttpResponse<JsonNode> response = request.asJson();
            Response<Object> r = new Response<Object>();
            r.setStatusCode(response.getStatus());
            r.setStatusText(response.getStatusText());
            r.setRawBody(getStringFromInputStream(response.getRawBody()));
            r.setParsedObject(response.getBody().isArray() ?
                    response.getBody().getArray() :
                    response.getBody().getObject());

            return r;
        } catch (UnirestException e) {
            throw new HttpClientException(e);
        }
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        if (is == null) return "";

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    
    public void close() throws Exception {
        Unirest.shutdown();
    }
}
