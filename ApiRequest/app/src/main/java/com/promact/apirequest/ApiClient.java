package com.promact.apirequest;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by grishma on 22-02-2017.
 */
public class ApiClient
{
    String HOST_URL="https://crozidevlopment.azurewebsites.net";
    HashMap<String,String>postParams,putParams;
    private static final String TAG = "ApiClient";
    // HTTP GET request
    public String sendGet() throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/api/user/drafts");
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCall(HOST_URL+"/api/user/drafts");
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }
    // HTTP POST request
    public String sendPost() throws Exception {
        String downloadString="";

        try {
            Log.d(TAG,"--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/api/stories");
            try {
                client.execute(RequestMethod.POST);
                postParams=new HashMap<>();
                postParams.put("CategoryId","-1");
                postParams.put("DateToCompare","");
                postParams.put("NumberOfStories","10");

                downloadString = client.performPostCall(HOST_URL+"/api/stories",postParams);
                System.out.println("--dwnld string is:"+downloadString);
                return downloadString;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return downloadString;
    }
    //HTTP PUT method
    public String sendPut() throws Exception {
        String downloadString="";
        try {
            Log.d(TAG,"--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/api/user");
            try {
                client.execute(RequestMethod.PUT);
                putParams=new HashMap<>();
                putParams.put("FirstName","Vishal");
                putParams.put("Id","3");
                downloadString = client.performPutCall(HOST_URL+"/api/user",putParams);
                Log.d("--dwnld string is:",downloadString);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }
    public String sendDelete() throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/api/device/156");
            try {
                client.execute(RequestMethod.DELETE);
                downloadString = client.performDeleteCall(HOST_URL+"/api/device/156");
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }
}
