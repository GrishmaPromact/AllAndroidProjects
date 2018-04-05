package com.promact.youtubedemoapp;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by grishma on 22-02-2017.
 */
public class ApiClient
{
    String HOST_URL="https://www.googleapis.com/youtube/v3";
    String HOST_URL_TO_SEND_REQUEST="https://accounts.google.com/o/oauth2";
    HashMap<String,String> postParams,putParams,getParams;
    private static final String TAG = "ApiClient";
    // HTTP GET request
    public String sendGet(String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/guideCategories?part=snippet&regionCode=IN");
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCall(HOST_URL+"/guideCategories?part=snippet&regionCode=IN",token);
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
    public String sendGetForChannels(String id,String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/channels?part=snippet&categoryId="+id+"&regionCode=IN");
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCategoryCall(HOST_URL+"/channels?part=snippet&categoryId="+id+"&regionCode=IN",token);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }

    public String sendGetForChannelsNextPage(String  id,String nextpageToken,String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/channels?part=snippet&categoryId="+id+"&pageToken="+nextpageToken);
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetCategoryCall(HOST_URL+"/channels?part=snippet&categoryId="+id+"&pageToken="+nextpageToken,token);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }

    public String sendGetForChannelsItem(String id,String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/search?part=snippet&channelId="+id+"&type=video");
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetChannelsItemsCall(HOST_URL+"/search?part=snippet&channelId="+id+"&type=video",token);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }

    public String sendGetForChannelsItemNextPage(String  id,String nextpageToken,String token) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient(HOST_URL+"/search?part=snippet&channelId="+id+"&type=video&pageToken="+nextpageToken);
            try {
                client.execute(RequestMethod.GET);
                downloadString = client.performGetChannelsItemsCall(HOST_URL+"/search?part=snippet&channelId="+id+"&type=video&pageToken="+nextpageToken,token);
                return downloadString;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadString;
    }
    public String sendPostRequestToOAuthServer(String code) throws Exception {
        String downloadString="";
        try {
            System.out.println("--------------in do background method--");
            HttpRestClient client = new HttpRestClient("https://accounts.google.com/o/oauth2/v2/auth?client_id=1054363319461-lsh62n42lmr4sr375v4ln3f92johk2go.apps.googleusercontent.com&redirect_uri=com.promact.youtubedemoapp:/oauth2redirect&prompt=consent&response_type=code&scope=email%20profile&state=security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.promact.com/token&pageId=none");
            try {
                client.execute(RequestMethod.POST);
                postParams=new HashMap<>();
                postParams.put("code",code);
                postParams.put("client_id","1054363319461-g2sfh8uevktnjia3iieac21t83jokrpt.apps.googleusercontent.com");
                postParams.put("client_secret","7ZRFFhdq3jxIjVFsQeY8WVfm");
                postParams.put("redirect_uri","https://developers.google.com/oauthplayground");
                postParams.put("grant_type","authorization_code");
               // postParams.put("client_secret","7ZRFFhdq3jxIjVFsQeY8WVfm");
                downloadString = client.sendPostRequestToGetToken("https://www.googleapis.com/oauth2/v4/token",postParams);
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
