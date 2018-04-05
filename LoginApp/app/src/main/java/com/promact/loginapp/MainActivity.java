package com.promact.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.App42NotFoundException;
import com.shephertz.app42.paas.sdk.android.user.UserService;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText  inputEmail, inputPassword;
    private TextInputLayout  inputLayoutEmail, inputLayoutPassword;
    private Button btnSignIn;
    private TextView signUp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Snackbar snackbar=null;
    private String AppKey = "869395760e25c7166c069f9d41689ea5cbef4ba03d2973d7953e526d438c206f";
    private String AppSecret = "fd6b29e11b0e8781490995a69fcb199520ce782e3082442418071d37ccf50f51";

    private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
      /*  if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationbar_color));
*/
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignIn = (Button) findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                getUser();
            }
        });
        signUp=(TextView)findViewById(R.id.signUp);
        signUp.setMovementMethod(LinkMovementMethod.getInstance());
        signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),SignUpActivity.class);
               startActivity(i);
           }
       });
    }

    private void getUser() {
         App42API.initialize(getApplicationContext(),AppKey,AppSecret);
         UserService userService = App42API.buildUserService();
         String unameText = inputEmail.getText().toString().trim();
        String upassText = inputPassword.getText().toString().trim();
        userService.authenticate(unameText,upassText, new App42CallBack()
        {
            //String unameText = inputEmail.getText().toString().trim();
            //String upassText = inputPassword.getText().toString().trim();
            public void onSuccess(Object response)
            {
               /* User user = (User)response;
                Log.d(TAG,"userName is :" + user.getUserName());
                String userName =user.getUserName();

                Log.d(TAG,"emailId is :" + user.getEmail());
                Log.d(TAG,"password is :" + user.getPassword());
                String userPassword=user.getPassword();

                Log.d(TAG,"First Name :"+user.getProfile().getFirstName());
                Log.d(TAG,"Last Name :"+user.getProfile().getLastName());

                if (unameText.equals(userName) && upassText.equals(userPassword)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                            .coordinatorLayout);
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Login successful..", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Handle user action
                                    snackbar.dismiss();
                                }
                            });
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                   // inputEmail.setText("");
                   // inputPassword.setText("");
                  }
                else if(unameText.equals(userName) && !upassText.equals(userPassword))
                {
                    final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                            .coordinatorLayout);
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Please check your password.", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Handle user action
                                    snackbar.dismiss();
                                }
                            });
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                   // inputPassword.setText("");
                }
                else if(!unameText.equals(userName) && upassText.equals(userPassword))
                {
                    final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                            .coordinatorLayout);
                    snackbar = Snackbar
                            .make(coordinatorLayout, "User does not exist.", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Handle user action
                                    snackbar.dismiss();
                                }
                            });
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                    //inputEmail.setText("");
                    //inputPassword.setText("");
                }
                else
                {
                    final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                            .coordinatorLayout);
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Invalid credentials..", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Handle user action
                                    snackbar.dismiss();
                                }
                            });
                    snackbar.setActionTextColor(Color.WHITE);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.DKGRAY);
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    snackbar.show();
                    inputEmail.setText("");
                    inputPassword.setText("");
                }*/
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
            public void onException(Exception ex)
            {
                try {
                    String exceptionMessage=ex.getMessage();
                    Log.d(TAG,"MESSAGE---------"+exceptionMessage);
                    try
                    {
                        JSONObject jsonObject=(new JSONObject(exceptionMessage)).getJSONObject("app42Fault");
                        int httpErrorCode = jsonObject.getInt("httpErrorCode");
                        Log.d(TAG,"HTTP ERROR CODE is:"+httpErrorCode);
                        if(httpErrorCode==404)
                        {
                            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                                    .coordinatorLayout);
                            snackbar = Snackbar
                                    .make(coordinatorLayout, "UserName or Password did not match. Authentication Failed.", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Ok", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Handle user action
                                            snackbar.dismiss();
                                        }
                                    });
                            snackbar.setActionTextColor(Color.WHITE);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(Color.DKGRAY);
                            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();
                        }

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                catch (App42NotFoundException exc)
                {
                    Log.d(TAG,"APP42 NOT FOUND EXCEPTION"+exc.getMessage());
                }
            }
        });
    }
}

