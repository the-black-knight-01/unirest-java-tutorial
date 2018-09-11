package com.unirest.UniRestDemo;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test against the http://httpbin.org service endpoints.
 */
public class HttpClientTest {

    @Test
    public void testGetRequest() throws Exception {
        try(HttpClient c = new HttpClient()) {
            Response<String> resp = c.get("http://httpbin.org/get", String.class);
            System.out.println(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testHeadRequest() throws Exception {
        try (HttpClient c = new HttpClient()) {
            Response<String> resp = c.head("http://httpbin.org/get", String.class);
            System.out.println(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testPostRequest() throws Exception {
        try (HttpClient c = new HttpClient()) {
            String data = "test";
            Response<Object> resp = c.post("http://httpbin.org/post", data);
            System.out.println(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testPutRequest() throws Exception {
        try (HttpClient c = new HttpClient()) {
            Map<String, String> data = new HashMap<>();
            data.put("isTest", "yes");
            Response<Object> resp = c.put("http://httpbin.org/put", data);
            System.out.println(resp);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testDeleteRequest() throws Exception {
        try (HttpClient c = new HttpClient()) {
            String data = "test";
            Response<Object> resp = c.delete("http://httpbin.org/delete", data);
            System.out.println(resp);
        } catch (Exception e) {
            throw e;
        }
    }
}
