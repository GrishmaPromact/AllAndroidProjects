package com.promact.youtubedemoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by grishma on 10-04-2017.
 */
public class LoginYoutubeActivity extends AppCompatActivity {

    private static String CLIENT_ID = "1054363319461-g2sfh8uevktnjia3iieac21t83jokrpt.apps.googleusercontent.com";
    //Use your own client id
    private static String CLIENT_SECRET = "7ZRFFhdq3jxIjVFsQeY8WVfm";
    //Use your own client secret
    private static String REDIRECT_URI = "https://developers.google.com/oauthplayground";
    private static String GRANT_TYPE = "authorization_code";
    private static String TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    private static String OAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static String OAUTH_SCOPE = "https://www.googleapis.com/auth/contacts.readonly";
    private static String OAUTH_STATE = "security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.promact.com/token";
    private SharedPreferences sharedPreferences;
    //Change the Scope as you need
    WebView web;
    Button auth;
    SharedPreferences pref;
    TextView Access;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("nextpagetokennn", Context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("isLogin", false);
        if (flag) {
            String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&prompt=consent&response_type=code&scope=" + OAUTH_SCOPE + "&state=" + OAUTH_STATE + "&pageId=none";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);

            finish();
           /* sharedPreferences.edit().putBoolean("isLogin", true).apply();
            Intent intent = new Intent(LoginYoutubeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();*/
        }
        setContentView(R.layout.activity_signin);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("YouTube");
        Button loginBtn = (Button) findViewById(R.id.loginYotubeBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&prompt=consent&response_type=code&scope=" + OAUTH_SCOPE + "&state=" + OAUTH_STATE + "&pageId=none";
                sharedPreferences.edit().putBoolean("isLogin", true).apply();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume");

        if (getIntent() != null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = getIntent().getData();
           /* if(uri.getQueryParameter("error") != null) {
                String error = uri.getQueryParameter("error");
                Log.e("error", "An error has occurred : " + error);
            }*/
            String state = uri.getQueryParameter("state");
            if (state.equals(OAUTH_STATE)) {
                String code = uri.getQueryParameter("code");
                Log.d("AUTH CODE:", code);
                //getAccessToken(code);
            }
        }
    }
}
  /*  private class TokenGet extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        String Code;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Contacting Google ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            Code = pref.getString("Code", "");
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            GetAccessToken jParser = new GetAccessToken();
            JSONObject json = jParser.gettoken(TOKEN_URL,Code,CLIENT_ID,CLIENT_SECRET,REDIRECT_URI,GRANT_TYPE);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            if (json != null){

                try {

                    String tok = json.getString("access_token");
                    String expire = json.getString("expires_in");
                    String refresh = json.getString("refresh_token");

                    Log.d("Token Access", tok);
                    Log.d("Expire", expire);
                    Log.d("Refresh", refresh);
                    auth.setText("Authenticated");
                    Access.setText("Access Token:"+tok+"nExpires:"+expire+"nRefresh Token:"+refresh);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }
    }*/


