package com.promact.apirequest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Info> arrayList;
    private ListAdapter adapter;
    private ListView listView;
    private static final String TAG = "ApiClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<Info>();
        listView = (ListView) findViewById(R.id.listview);
        Log.d(TAG, "ArrayList is:" + arrayList.size());
    }
    /*AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Info>>() {
        @Override
        public void onTaskComplete(ArrayList<Info> serverArrayList) {
            adapter = new ProductListAdapter(getApplicationContext(), R.layout.listview_layout, serverArrayList);
            listView.setAdapter(adapter);
        }
    }).execute();*/
    AsyncTask postResponseFromServerTask = new PostResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Info>>() {
        @Override
        public void onTaskComplete(ArrayList<Info> serverArrayList) {
            adapter = new ProductListAdapter(getApplicationContext(), R.layout.listview_layout, serverArrayList);
            listView.setAdapter(adapter);
        }
    }).execute();
   /* AsyncTask putResponseFromServerTask = new PutResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Info>>() {
        @Override
        public void onTaskComplete(ArrayList<Info> serverArrayList) {
            adapter = new ProductListAdapter(getApplicationContext(), R.layout.listview_layout, serverArrayList);
            listView.setAdapter(adapter);
        }
    }).execute();*/
   /* AsyncTask deleteResponseFromServerTask = new DeleteResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Info>>() {
        @Override
        public void onTaskComplete(ArrayList<Info> serverArrayList) {
            adapter = new ProductListAdapter(getApplicationContext(), R.layout.listview_layout, serverArrayList);
            listView.setAdapter(adapter);
        }
    }).execute();
*/
  /*  private class getResponseFromServerTask extends AsyncTask<String,String,String> {
        private Activity context;
        private ProgressDialog progressDialog;
        private String downloadJSONString;
        @Override
        protected String doInBackground(String... strings)
        {
            String response="";
            try {
                //response=sendPost();
                //response=sendGet();
              // response=sendPut();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        @Override
        protected void onPostExecute(String response) {
            ArrayList<Info> serverArrayList = new ArrayList<Info>();
            *//* sharedPreferences = getSharedPreferences("jsonparsing", Context.MODE_PRIVATE);
            downloadJSONString = sharedPreferences.getString("response" , "");
            Log.d(TAG, "download JSON string:" + downloadJSONString);*//*
            Log.d("----------response-----",response);
            if (response != null) {
                JSONArray jsonArray ;
                try {

                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String serverId = jsonObject.getString("id");
                        String serverTitle = jsonObject.getString("title");
                        String serverDescripption = jsonObject.getString("description");
                        serverArrayList.add(new Info(serverId, serverTitle, serverDescripption));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            adapter = new ProductListAdapter(getApplicationContext(), R.layout.listview_layout, serverArrayList);
            listView.setAdapter(adapter);
        }
    }*/
}




//http get request without using http rest client class
/*  String url = "https://crozidevlopment.azurewebsites.net/api/user/drafts";

       URL obj = new URL(url);
       HttpURLConnection con = (HttpURLConnection) obj.openConnection();

       // optional default is GET
       con.setRequestMethod("GET");

       //add request header
       //con.setRequestProperty("User-Agent", USER_AGENT);
       con.setRequestProperty("X-ZUMO-AUTH", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdGFibGVfc2lkIjoic2lkOmJkNzNiMTVkNDJiNDJmODhmMjIxMDAwOTE0YzA0NDIyIiwic3ViIjoic2lkOmY0NDdjYzhhYmM0MTU3NDE1NmFiYjZhOTg2NTRhMDkxIiwiaWRwIjoiZmFjZWJvb2siLCJ2ZXIiOiIzIiwiaXNzIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiYXVkIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiZXhwIjoxNDg3NDA2MjQ1LCJuYmYiOjE0ODIyMzQxNDB9.MIwdXgPXX9zvwN8Vd9R1z9lKW-hHJWXwG-zs42kxS8c");
       con.setRequestProperty("ZUMO-API-VERSION", "2.0.0");

       int responseCode = con.getResponseCode();
       Log.d("\nSending 'GET' request to URL : " , url);
       Log.d("Response Code : " , String.valueOf(responseCode));

       BufferedReader in = new BufferedReader(
               new InputStreamReader(con.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();

       while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
       }
       in.close();

       //print result
       Log.d("",response.toString());*/

//http post request without using http rest client class

   /*String url = "https://crozidevlopment.azurewebsites.net/api/stories";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("X-ZUMO-AUTH", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdGFibGVfc2lkIjoic2lkOmJkNzNiMTVkNDJiNDJmODhmMjIxMDAwOTE0YzA0NDIyIiwic3ViIjoic2lkOmY0NDdjYzhhYmM0MTU3NDE1NmFiYjZhOTg2NTRhMDkxIiwiaWRwIjoiZmFjZWJvb2siLCJ2ZXIiOiIzIiwiaXNzIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiYXVkIjoiaHR0cHM6Ly9jcm96aWRldmxvcG1lbnQuYXp1cmV3ZWJzaXRlcy5uZXQvIiwiZXhwIjoxNDg3NDA2MjQ1LCJuYmYiOjE0ODIyMzQxNDB9.MIwdXgPXX9zvwN8Vd9R1z9lKW-hHJWXwG-zs42kxS8c");
        con.setRequestProperty("ZUMO-API-VERSION", "2.0.0");

        String urlParameters = "CategoryId=-1&DateToCompare=&NumberOfStories=10";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        Log.d("\nSending 'POST' request to URL : " ,url);
        Log.d("Post parameters : " , urlParameters);
        Log.d("Response Code : " , String.valueOf(responseCode));

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
       Log.d("",response.toString());
        sharedPreferences = getSharedPreferences("jsonparsing", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("response" , response.toString()).apply();
        editor.commit();*/
