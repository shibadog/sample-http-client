package com.github.shibadog.sample.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Application {

    public static void main(String[] args) {
        HttpMethod method = new PostMethod("http://localhost:8080/");
        HttpMethodParams methodParam = new HttpMethodParams();
        methodParam.setContentCharset("utf-8, iso-8859-1;q=0.5");
        method.setParams(methodParam);

        method.setRequestHeader("Content-Type", "application/json");

        HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();

        managerParams.setConnectionTimeout(100 * 1000);
        managerParams.setSoTimeout(1 * 1000);

        HttpConnectionManager httpConnectionManager = new SimpleHttpConnectionManager();
        httpConnectionManager.setParams(managerParams);

        HttpClient client = new HttpClient(httpConnectionManager);
        client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(0, false));

        try {
            client.executeMethod(method);
            System.out.println(method.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
    }

}
