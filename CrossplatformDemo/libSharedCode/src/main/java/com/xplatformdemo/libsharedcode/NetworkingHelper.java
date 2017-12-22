package com.xplatformdemo.libsharedcode;

/**
 * Created by Morten on 18/12/2017.
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.*;

public class NetworkingHelper {

    private <T> T execute(Callable<T> callable) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> result = executor.submit(callable);
        try {
            return result.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw (e);
        } finally {
            executor.shutdown();
        }
    }

    public boolean createUser(final String username, final String password) throws Exception {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    URL serverUrl = new URL("http://codesharing.getsandbox.com/user/"+username+"/"+password);
                    HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setRequestMethod("POST");

                    int responseCode = httpCon.getResponseCode();
                    return responseCode == 200;
                } catch (Exception e) {
                    throw (e);
                }
            }
        };
        return execute(callable).booleanValue();
    }

    public boolean login(String username, String password) throws Exception {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    URL serverUrl = new URL("http://codesharing.getsandbox.com/login/"+username+"/"+password);
                    HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setRequestMethod("POST");

                    int responseCode = httpCon.getResponseCode();
                    return responseCode == 200;
                } catch (Exception e) {
                    throw (e);
                }
            }
        };
        return execute(callable).booleanValue();
    }

    public JSONArray getData() throws Exception {
        Callable<JSONArray> callable = new Callable<JSONArray>() {
            @Override
            public JSONArray call() throws Exception {
                try {
                    URL serverUrl = new URL("http://codesharing.getsandbox.com/users");
                    HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return new JSONArray(response.toString());
                } catch (Exception e) {
                    throw(e);
                }
            }
        };
        return execute(callable);
    }
}
