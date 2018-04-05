/*
package com.promact.youtubedemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

//Created by grishma on 20-01-2017.


public class ProfileInformationActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener
{

    //TextViews
    private TextView textViewName;
    private TextView textViewEmail;
    private NetworkImageView profilePhoto;
    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;

    //Image Loader
    private ImageLoader imageLoader;
    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Signing Options
    private GoogleSignInOptions gso;
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initializing Views
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        profilePhoto = (NetworkImageView) findViewById(R.id.profileImage);
        signOutButton = (Button) findViewById(R.id.sign_out_button);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this */
/* FragmentActivity *//*
, this */
/* OnConnectionFailedListener *//*
)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signIn();
        signOutButton.setOnClickListener(this);
    }
    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(true);
                        // [END_EXCLUDE]
                    }
                });
    }
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            System.out.println("-----HELLO---------");
            Intent i=new Intent(getApplicationContext(),GoogleSignInAvtivity.class);
            startActivity(i);
            finish();
        }
    }
        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            String accountName = acct.getDisplayName();
            System.out.println(accountName);
           
           */
/* String token="";
            try {
                token = GoogleAuthUtil.getToken(getApplicationContext(), accountName,scopes);
                System.out.println(token);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GoogleAuthException e) {
                e.printStackTrace();
            }*//*


            //Displaying name and email
            textViewName.setText(acct.getDisplayName());
            textViewEmail.setText(acct.getEmail());

            //Initializing image loader
            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                    .getImageLoader();

                imageLoader.get(acct.getPhotoUrl().toString(),
                        ImageLoader.getImageListener(profilePhoto,
                                R.mipmap.ic_launcher,
                                R.mipmap.ic_launcher));
                //Loading image
                profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);

           */
/* Intent i=new Intent(getApplicationContext(),ProfileInformationActivity.class);
            startActivity(i);*//*


        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    public void onClick(View v) {
        if (v == signOutButton) {
            //Calling signin
            signOut();
        }
    }

}
*/
