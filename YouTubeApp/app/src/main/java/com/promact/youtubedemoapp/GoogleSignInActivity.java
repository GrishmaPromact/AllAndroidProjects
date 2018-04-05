/*package com.promact.youtubedemoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class GoogleSignInActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Dialog auth_dialog;
    private WebView webView;
    private static String CLIENT_ID = "1054363319461-g2sfh8uevktnjia3iieac21t83jokrpt.apps.googleusercontent.com";
    //Use your own client id
    private static String CLIENT_SECRET ="7ZRFFhdq3jxIjVFsQeY8WVfm";
    //Use your own client secret
    private static String REDIRECT_URI="https://developers.google.com/oauthplayground";
    private static String GRANT_TYPE="authorization_code";
    private static String TOKEN_URL ="https://accounts.google.com/o/oauth2/token";
    private static String OAUTH_URL ="https://accounts.google.com/o/oauth2/auth";
    private static String OAUTH_SCOPE="email%20profile";
    private static String OAUTH_STATE="security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.promact.com/token";


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("YouTube");
        Button loginBtn = (Button) findViewById(R.id.loginYotubeBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth_dialog=new Dialog(GoogleSignInActivity.this);
                auth_dialog.setContentView(R.layout.auth_dialog);
                webView=(WebView)auth_dialog.findViewById(R.id.webv);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(OAUTH_URL+"?clilent_id="+CLIENT_ID+"&redirect_url="+REDIRECT_URI+"&prompt=consent&response_type=code&scope="+OAUTH_SCOPE+"&state="+OAUTH_STATE+"&pageId=none");
                webView.setWebViewClient(new WebViewClient() {

                    boolean authComplete = false;
                    Intent resultIntent = new Intent();

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon){
                        super.onPageStarted(view, url, favicon);

                    }
                    String authCode;
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        if (url.contains("?code=") && authComplete != true) {
                            Uri uri = Uri.parse(url);
                            authCode = uri.getQueryParameter("code");
                            Log.i("", "CODE : " + authCode);
                            authComplete = true;
                            resultIntent.putExtra("code", authCode);
                            MainActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                            setResult(Activity.RESULT_CANCELED, resultIntent);

                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("Code", authCode);
                            edit.commit();
                            auth_dialog.dismiss();
                            new TokenGet().execute();
                            Toast.makeText(getApplicationContext(),"Authorization Code is: " +authCode, Toast.LENGTH_SHORT).show();
                        }else if(url.contains("error=access_denied")){
                            Log.i("", "ACCESS_DENIED_HERE");
                            resultIntent.putExtra("code", authCode);
                            authComplete = true;
                            setResult(Activity.RESULT_CANCELED, resultIntent);
                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();

                            auth_dialog.dismiss();
                        }
                    }



                });
            }
        });
    }
}*/
/*
public class GoogleSignInActivity extends AppCompatActivity {

    //Signin button
   */
/* private SignInButton signInButton;
    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Signin constant to check the activity result

    private static final int REQ_SIGN_IN_REQUIRED = 55664;*//*

    private SharedPreferences sharedPreferences;
    private int RC_SIGN_IN = 100;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("hello", Context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("isLogin", false);
        if (flag) {
            ///second time activity
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_signin);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);
        toolbar.setTitle("YouTube");
        Button loginBtn = (Button) findViewById(R.id.loginYotubeBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ("https://accounts.google.com/o/oauth2/v2/auth?client_id=1054363319461-g2sfh8uevktnjia3iieac21t83jokrpt.apps.googleusercontent.com&redirect_uri=https://developers.google.com/oauthplayground&prompt=consent&response_type=code&scope=email%20profile&state=security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.promact.com/token&pageId=none");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //i.setData(Uri.parse(url));

                    Uri uri = Uri.parse(url);

                    String authCode = uri.getQueryParameter("code");
                    System.out.println( "CODE : " +authCode);


                startActivityForResult(i, RC_SIGN_IN);
            }
        });

    }

       */
/* //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //Initializing signinbutton
        *//*
*/
/*signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this *//*
*/
/**//*
*/
/* FragmentActivity *//*
*/
/**//*
*/
/*, this *//*
*/
/**//*
*/
/* OnConnectionFailedListener *//*
*/
/**//*
*/
/*)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);*//*
*/
/*
    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            Toast.makeText(GoogleSignInActivity.this, "Result", Toast.LENGTH_SHORT).show();
            *//*
*/
/*GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);*//*
*/
/*
        }

    }

    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            sharedPreferences.edit().putBoolean("isLogin", true).apply();
            String accntName = result.getSignInAccount().getDisplayName();
            Log.d("accnt name:", accntName);
            String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id=1054363319461-lsh62n42lmr4sr375v4ln3f92johk2go.apps.googleusercontent.com&redirect_uri=com.promact.youtubedemoapphttps/auth/moves/callback&prompt=consent&response_type=code&scope=email%20profile&state=security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.promact.com/token&pageId=none";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            //i.setData(Uri.parse(url));
            startActivityForResult(i,RC_SIGN_IN);
            //new RetrieveTokenTask().execute(accntName);
            //new GetResponseFromOauthServerTask(getApplicationContext()).execute();
            *//*
*/
/*Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();*//*
*/
/*
        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }*//*


    */
/*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // [START_EXCLUDE]
                            updateUI(true);
                            // [END_EXCLUDE]
                        }

                        private void updateUI(boolean signedIn)
                        {
                            if (signedIn) {
                                System.out.println("-----HELLO---------");
                                Intent i=new Intent(getApplicationContext(),GoogleSignInActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
            return true;
        }
        return super.onOptionsItemSelected(item);

       *//*
*/
/* Drawer.closeDrawer(GravityCompat.START);
        return true;*//*
*/
/*
    }
*//*

   */
/* private class RetrieveTokenTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String accountName = params[0];
            String scopes = "oauth2:profile email";
            String token = null;
            try {
                token = GoogleAuthUtil.getToken(getApplicationContext(), accountName, scopes);
                Log.d("TOKEN:", token);
            } catch (IOException e) {
                Log.e("error", e.getMessage());
            } catch (UserRecoverableAuthException e) {
                startActivityForResult(e.getIntent(), REQ_SIGN_IN_REQUIRED);
                //REQ_SIGN_IN_REQUIRED = 55664;
            } catch (GoogleAuthException e) {
                Log.e("error", e.getMessage());
            }
            return token;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("AccessToken" + s);
        }
    }*//*

    public class GetResponseFromOauthServerTask extends AsyncTask<String,String,String> {

        private Context mContext;
        private CategoryResponse.Items items;
        private String nextpageToken;
        public GetResponseFromOauthServerTask(Context context) {
            mContext = context;
            this.items=items;
            this.nextpageToken=nextpageToken;
        }
        @Override
        protected String doInBackground(String... strings)
        {
            //isScrollingDown = true;
            String response="";
            try {
                response=new ApiClient().sendGetRequestToOAuthServer();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        @Override
        protected void onPostExecute(String response) {
            Log.d("----------response-----",response);
            */
/*Gson gson = new GsonBuilder().create();
            CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
            categoryChannelItems.addAll(categoriesChannels.getItems());
            ((ArrayAdapter<CategoryChannelsResponse.Items1>) adapter).notifyDataSetChanged();
       *//*
 }
    }
}*/
