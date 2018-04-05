package com.promact.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grishma on 02-06-2017.
 */
public class UsersActivity extends AppCompatActivity{
    private ListView listView;
    private ListAdapter listViewAdapter;
    private ArrayList<LoginResponse>loginResponseList;
    private ArrayList<String> getAllUsersResponseArrayList=new ArrayList<>();
    private ArrayList<GetAllUsersResponse>userResponseList=new ArrayList<>();
    private int totalUsers=0;
    private TextView noUsersText;
    private static final String TAG="UsersActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        noUsersText=(TextView)findViewById(R.id.noUsersText);
        Intent i=getIntent();
        loginResponseList=i.getParcelableArrayListExtra("token");
        String authToken="";
        String userIdOfLogin="";
        for (int j = 0; j <loginResponseList.size() ; j++) {
            authToken=loginResponseList.get(j).getToken();
            userIdOfLogin=loginResponseList.get(j).getId();
            Log.d(TAG,"Auth token is:"+authToken);
            Log.d(TAG,"login user id is:"+userIdOfLogin);
        }
       listView = (ListView) findViewById(R.id.usersList);
        new GetAllUsersTask(authToken, new IDownloadAllUsersResponse<GetAllUsersResponse>() {
            @Override
            public void onTaskComplete(List<GetAllUsersResponse> downlodGetAllUsersArrayList) {
                //do nothing
                userResponseList.addAll(downlodGetAllUsersArrayList);
                String name="";
                for (int i = 0; i < downlodGetAllUsersArrayList.size(); i++) {
                    name=downlodGetAllUsersArrayList.get(i).getName();
                    doOnSuccess(name);
                }
                Log.d(TAG,"Users name from response"+name);
            }
        }).execute();
        final String finalAuthToken = authToken;
        final String finalUserIdOfLogin = userIdOfLogin;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = getAllUsersResponseArrayList.get(position);
                GetAllUsersResponse userId=userResponseList.get(position);
                String  loginUserId=userId.getId();
                Log.d(TAG,"User id of on click--------- : : "+loginUserId);
                Intent i = new Intent(UsersActivity.this, ChatActivity.class);
                // set the new task and clear flags
                i.putExtra("userId", loginUserId);
                i.putExtra("userIdLogin", finalUserIdOfLogin);
                i.putExtra("authToken", finalAuthToken);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }



    private void doOnSuccess(String usersName) {

        getAllUsersResponseArrayList.add(usersName);
        Log.d(TAG,"arraylist size:"+getAllUsersResponseArrayList.size());
        totalUsers++;

        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getAllUsersResponseArrayList));
        }
    }

    private class GetAllUsersTask extends AsyncTask<String,String,String>
    {
        private String authToken;
        private ProgressDialog progressDialog;
        private IDownloadAllUsersResponse<GetAllUsersResponse> callback;
        GetAllUsersTask(String authToken,IDownloadAllUsersResponse<GetAllUsersResponse>cb)
        {
            this.authToken=authToken;
            this.callback=cb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(UsersActivity.this);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String downloadResponseOfAllUsers="";
            try{
                downloadResponseOfAllUsers=new ApiClient().sendGet(authToken);
                Log.d(TAG,"Download json string response of all users is:"+downloadResponseOfAllUsers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return downloadResponseOfAllUsers;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            progressDialog.dismiss();
            Gson gson=new Gson();
            GetAllUsersResponse[] getAllUsersResponse = gson.fromJson(response, GetAllUsersResponse[].class);
            Type listType = new TypeToken<List<GetAllUsersResponse>>(){}.getType();
            List<GetAllUsersResponse> posts = (List<GetAllUsersResponse>) gson.fromJson(response, listType);
            callback.onTaskComplete(posts);
        }
    }
}
