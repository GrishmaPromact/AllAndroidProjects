package com.promact.chatapp;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by grishma on 22-02-2017.
 */
public class ApiClient
{
    String HOST_URL="http://10.1.81.210:5555/api";
    //String HOST_URL_TO_SEND_REQUEST="https://accounts.google.com/o/oauth2";
    HashMap<String,String> postParams,putParams,getParams;
    private static final String TAG = "ApiClient";
    // HTTP GET request
    public String sendGet(String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/user");
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCall(HOST_URL+"/user",token);
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
            HttpRestClient client = new HttpRestClient(HOST_URL+"/api/login");
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
    public String sendPostRequestToServer(String userName) {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/user/login");
            try {
                client.execute(RequestMethod.POST);
                Log.d("Username in post method:",userName);
                postParams=new HashMap<>();
                postParams.put("name",userName);
                downloadString = client.sendPostRequestToGetToken(HOST_URL+"/user/login",postParams);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }

    public String getAllChatMsgsWithUser(String  userId,String authToken) {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/chat/"+userId);
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCall(HOST_URL+"/chat/"+userId,authToken);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }

    public String sendPostReqToSendMsg(String authToken, String userId, String msgText) {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/chat");
            try {
                client.execute(RequestMethod.POST);
                Log.d("Authtoken in post method:",authToken);
                Log.d("userId in post method:",userId);
                Log.d("msgtext in post method:",msgText);
                postParams=new HashMap<>();
                postParams.put("message",msgText);
                postParams.put("toUserId",userId);
                downloadString = client.sendPostRequestToSengMsg(HOST_URL+"/chat",postParams,authToken);
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
