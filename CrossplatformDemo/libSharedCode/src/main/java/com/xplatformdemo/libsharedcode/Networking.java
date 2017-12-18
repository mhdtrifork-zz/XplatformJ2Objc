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


public class Networking {

    private static <T> T execute(Callable<T> callable) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> result = executor.submit(callable);
        try {
            return result.get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            executor.shutdown();
        }
    }

    public static Boolean createUser(final String username, final String password) throws Exception {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    URL serverUrl = new URL("http://codesharing.getsandbox.com/user/"+username+"/"+password);
                    HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setRequestMethod("POST");

                    int responseCode = httpCon.getResponseCode();
                    System.out.println("test"+responseCode);
                    return responseCode == 200;
                } catch (Exception e) {
                    throw (e);
                }
            }
        };
        return execute(callable);
    }

    /*public static void login(String username, String password) throws Exception {
        Runnable runAble = new Runnable() {
            @Override
            public void run() {
                try {
                    URL serverUrl = new URL("http://codesharing.getsandbox.com/users");
                    HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setRequestMethod("GET");

                    String test = "{username:\"test\",password\"1234\"}";

                    //OutputStream op = httpCon.getOutputStream();
                    //DataOutputStream wr = new DataOutputStream(op);
                    // wr.writeBytes(test);
                    // wr.flush();
                    //wr.close();
                    //op.close();


                    int responseCode = httpCon.getResponseCode();
                    System.out.println("\nSending 'POST' request to URL : " + serverUrl);
                    System.out.println("Post parameters : " + test);
                    System.out.println("Response Code : " + responseCode);


                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(httpCon.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(response.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(runAble);


        // OutputStream output = httpCon.getOutputStream();
        //output.write(test.getBytes("UTF-8"));

        //InputStream response = httpCon.getInputStream();
        //ConsoleHandler logger = new ConsoleHandler();
        //logger.publish(new LogRecord(Level.ALL, response.toString()));
    }*/
}
