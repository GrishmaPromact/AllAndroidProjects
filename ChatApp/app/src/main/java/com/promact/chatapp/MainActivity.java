package com.promact.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView userName;
    private Button loginButton;
    private String user;
    private TextInputLayout inputLayoutUsername;
    private static final String TAG="MainActivity";
    private ArrayList<LoginResponse> loginResponseArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputLayoutUsername=(TextInputLayout)findViewById(R.id.input_layout_uname);
        userName=(TextView)findViewById(R.id.input_uname);
        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = userName.getText().toString();
                Log.d(TAG,"User name is:"+user);
                String userLength= String.valueOf(user.length());
                if(user.equals("")){
                    userName.setError("can't be blank");
                }
                else if(userLength.equals(" "))
                {
                    userName.setError("Space is not allowed into username.");
                }
                else
                {
                    new TokenGet(user, new IDownloadLoginResponse<LoginResponse>() {
                        @Override
                        public void onTaskComplete(LoginResponse loginResponse) {
                            loginResponseArrayList.add(loginResponse);
                            String token=loginResponse.getToken();
                        Intent i=new Intent(MainActivity.this,UsersActivity.class);
                            i.putExtra("token",loginResponseArrayList);
                            startActivity(i);
                        }
                    }).execute();
                }
            }
        });
    }

    private class TokenGet extends AsyncTask<String,String,String>
    {
        private String userName;
        private ProgressDialog progressDialog;
        private IDownloadLoginResponse<LoginResponse> callback;
        public TokenGet(String userName,IDownloadLoginResponse<LoginResponse>cb)
        {
            this.userName=userName;
            this.callback=cb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response="";
            try{
                response=new ApiClient().sendPostRequestToServer(userName);
                Log.d(TAG,"LoginApi reponse is:"+response);
            } catch (Exception e) {
                Log.d(TAG,"Exception msg",e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            progressDialog.dismiss();
            Log.d(TAG,"LoginApi reponse in post method is:"+response);
            Gson gson=new Gson();
            LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
            callback.onTaskComplete(loginResponse);
            String userNameAfterResponse = loginResponse.getName();
            Log.d(TAG,"User Name is:"+userNameAfterResponse);
            //sendRequest(userNameAfterResponse);
        }
    }
}
