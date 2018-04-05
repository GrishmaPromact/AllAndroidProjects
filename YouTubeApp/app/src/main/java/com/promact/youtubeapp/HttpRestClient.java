package com.promact.youtubeapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by grishma on 07-02-2017.
 */
public class HttpRestClient
{
    private HashMap<String,String> headers;
    private HashMap<String,String> params;
    String HOST_URL = "https://www.googleapis.com/youtube/v3";
   // String accessToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdGFibGVfc2lkIjoic2lkOmJkNzNiMTVkNDJiNDJmODhmMjIxMDAwOTE0YzA0NDIyIiwic3ViIjoic2lkOmY0NDdjYzhhYmM0MTU3NDE1NmFiYjZhOTg2NTRhMDkxIiwiaWRwIjoiZmFjZWJvb2siLCJ2ZXIiOiIzIiwiaXNzIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiYXVkIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiZXhwIjoxNDkyOTE1MzE4LCJuYmYiOjE0ODc3MzM5MDB9.YqpK5wESXTCvbBZLW6MzaCqb3GIOCqgheNH05VZ4GD8";
    protected Context context;

    public HttpRestClient(String url) {
        HOST_URL = HOST_URL + url;
        params = new HashMap<>();
        headers = new HashMap<>();
    }
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public void execute(RequestMethod method) throws Exception {
        switch (method) {
            case GET: {
             //  performGetCall(HOST_URL);
                addGetDataParams(params);
                addHeaderParams(headers);
                break;
            }
            case POST: {
               /* HttpPost request = new HttpPost(url);
                request = (HttpPost) addHeaderParams(request);
                request = (HttpPost) addBodyParams(request);
                executeRequest(request, url);*/
                //performPostCall(HOST_URL,params);
                addHeaderParams(headers);
                break;
            }
            case PUT: {
               /* HttpPut request = new HttpPut(url);
                request = (HttpPut) addHeaderParams(request);
                request = (HttpPut) addBodyParams(request);
                executeRequest(request, url);*/
                addHeaderParams(headers);
                break;
            }
            case DELETE: {
               /* HttpDelete request = new HttpDelete(url);
                request = (HttpDelete) addHeaderParams(request);
                executeRequest(request, url);*/
                addGetDataParams(params);
                addHeaderParams(headers);
            }

        }
    }
    private String addHeaderParams(HashMap<String, String> headers) throws UnsupportedEncodingException {
        for (HashMap.Entry<String, String> entry : headers.entrySet()) {
            headers.put(entry.getKey(), entry.getValue());
        }
        return headers.toString();
    }
    public String performGetCall(String requestURL) throws Exception {

        URL url = new URL(requestURL);
        System.out.println("---URL--"+url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Content-length", "0");
        con.addRequestProperty("Authorization", "Bearer ya29.GlsIBALkFc7bYOGfHnjTKeZuWMXPoNrVegsbZC9fblA7-hMYIEfd2_HPLsKD4I0QrYeaoywac6eFmxfc_h0U7PNxpDNsKmz77oKn1y7mpf5ytDEJ0uMs0b2E6hcS");
        int responseCode = con.getResponseCode();
        //Log.d("\nSending 'GET' request to URL : " , String.valueOf(url));
        Log.d("Response Code : " , String.valueOf(responseCode));
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        Log.d("",response.toString());
        return response.toString();
    }
    public String addGetDataParams(HashMap<String, String> params) throws UnsupportedEncodingException {
        //Using StringBuffer append for better performance.
        StringBuffer result = new StringBuffer();
        if (!params.isEmpty()) {
            result.append("?");
            for (HashMap.Entry<String, String> p : params.entrySet()) {
                //request.addRequestProperty(entry.getKey(), entry.getValue());
                result.append((result.length() > 1 ? "&" : "")
                        + p.getKey() + "="
                        + URLEncoder.encode(p.getValue(), "UTF-8"));
                params.put(p.getKey(),p.getValue());
            }
        }
        return result.toString();
    }
    public String performPostCall(String requestURL,
                                  HashMap<String, String> postDataParams) {
       URL url;
       String response = "";
       try {
           url = new URL(requestURL);
           System.out.println("---URL--"+url);

           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setReadTimeout(15000);
           conn.setConnectTimeout(7000);
           conn.setRequestMethod("POST");
           conn.setDoInput(true);
           conn.setDoOutput(true);
          // conn.setRequestProperty("X-ZUMO-AUTH", accessToken);
          // conn.addRequestProperty("ZUMO-API-VERSION", "2.0.0");

           OutputStream os = conn.getOutputStream();
           BufferedWriter writer = new BufferedWriter(
                   new OutputStreamWriter(os, "UTF-8"));
           writer.write(addPostDataParams(postDataParams));

           writer.flush();
           writer.close();
           os.close();
           int responseCode=conn.getResponseCode();

           if (responseCode == HttpsURLConnection.HTTP_OK) {
               String line;
               BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
               while ((line=br.readLine()) != null) {
                   response+=line;
               }
           }
           else {
               response="";

           }
       } catch (Exception e) {
           e.printStackTrace();
       }

       return response.toString();
   }
    private String addPostDataParams(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            params.put(entry.getKey(),entry.getValue());
        }
        return result.toString();
    }
    public String performPutCall(String requestURL,
                                 HashMap<String, String> putDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            System.out.println("---URL--"+url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(7000);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);
           // conn.setRequestProperty("X-ZUMO-AUTH",accessToken);
            //conn.addRequestProperty("ZUMO-API-VERSION", "2.0.0");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(addPostDataParams(putDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
    public String performDeleteCall(String requestURL) throws Exception {

        URL url = new URL(requestURL);
        System.out.println("---URL--"+url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // optional default is GET
        con.setRequestMethod("DELETE");
        //add request header
        //con.setRequestProperty("X-ZUMO-AUTH", accessToken);
       // con.addRequestProperty("ZUMO-API-VERSION", "2.0.0");
        int responseCode = con.getResponseCode();
        //Log.d("\nSending 'GET' request to URL : " , String.valueOf(url));
        Log.d("Response Code : " , String.valueOf(responseCode));
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        Log.d("",response.toString());
        return response.toString();
    }
}
