package com.promact.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42BadParameterException;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grishma on 16-11-2016.
 */
public class SignUpActivity extends AppCompatActivity
{
    private ActionBar actionBar;
    private String email,password;
    private String TAG="SignUpActivity";
    private Snackbar snackbar=null;
    private String AppKey = "869395760e25c7166c069f9d41689ea5cbef4ba03d2973d7953e526d438c206f";
    private String AppSecret = "fd6b29e11b0e8781490995a69fcb199520ce782e3082442418071d37ccf50f51";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText firstName,lastName,emailId,signupPassword,confirmPassword;
    private TextInputLayout inputLayoutFirstname,inputLayoutLastName,inputLayoutEmail,inputLayoutPassword,inputLayoutConfirmPassword;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("SignUp");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        firstName=(EditText)findViewById(R.id.input_firstname);
        lastName=(EditText)findViewById(R.id.input_lastname);
        emailId=(EditText)findViewById(R.id.input_emailid) ;
        signupPassword=(EditText)findViewById(R.id.input_signuppassword);
        confirmPassword=(EditText)findViewById(R.id.input_confirmpassword);

        inputLayoutFirstname=(TextInputLayout)findViewById(R.id.input_layout_firstname);
        inputLayoutLastName=(TextInputLayout)findViewById(R.id.input_layout_lastname);
        inputLayoutEmail=(TextInputLayout)findViewById(R.id.input_layout_emailid);
        inputLayoutPassword=(TextInputLayout)findViewById(R.id.input_layout_signuppassword);
        inputLayoutConfirmPassword=(TextInputLayout)findViewById(R.id.input_layout_confirmpassword);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                validateEmail();
                validatePassword();
                validateConfirmPassword();
                createUserEntry();
            }
        });
        emailId.addTextChangedListener(new MyTextWatcher(emailId));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
            case R.id.action_menu:

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean validateEmail() {
        email = emailId.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(emailId);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validatePassword(){
     password=signupPassword.getText().toString().trim();
        if (password.isEmpty() || !isValidPassword(password))
        {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(signupPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }
    private static boolean isValidPassword(String password)
    {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,25}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private boolean validateConfirmPassword(){
        String confmPassword=confirmPassword.getText().toString().trim();
        String password=signupPassword.getText().toString().trim();
        if (confmPassword.isEmpty() || !isValidConfirmPassword(confmPassword,password))
        {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password));
            requestFocus(confirmPassword);
            return false;
        } else {
            inputLayoutConfirmPassword.setErrorEnabled(false);
        }
        return true;
    }
    private static boolean isValidConfirmPassword(String confmPassword, String password)
    {
        boolean pstatus = false;
        if (confmPassword != null && password != null)
        {
            if (password.equals(confmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_emailid:
                    validateEmail();
                    break;
                case R.id.input_signuppassword:
                    validatePassword();
                    break;
                case R.id.input_confirmpassword:
                    validateConfirmPassword();
                    break;
            }
        }
    }
    private void createUserEntry()
    {
        App42API.initialize(getApplicationContext(),AppKey,AppSecret);
        UserService userService = App42API.buildUserService();
        final String uname = emailId.getText().toString().trim();


        String upass=signupPassword.getText().toString().trim();
        String uemail=emailId.getText().toString().trim();
        String firstNameUser=firstName.getText().toString().trim();
        String lastnameUser=lastName.getText().toString().trim();
        //password=signupPassword.getText().toString().trim();
        userService.createUser( uname, upass,uemail,new App42CallBack() {
        public void onSuccess(Object response)
        {
            User user = (User)response;
            Log.d(TAG,"username is " + user.getUserName());
            String userNameDatabase=user.getUserName();

            Log.d(TAG,"emailId is " + user.getEmail());
            Log.d(TAG,"password is " + user.getPassword());
            String userPasswordDatabase=user.getPassword();

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
                    if(httpErrorCode==400)
                    {
                        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id
                                .cordinatorLayout);
                        snackbar = Snackbar
                                .make(coordinatorLayout, "Email address is already exists.Please enter another email address.", Snackbar.LENGTH_INDEFINITE)
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
            catch (App42BadParameterException exc)
            {
               Log.d(TAG,"APP42 BAD PARAMETER EXCEPTION"+exc.getMessage());
            }
        }
        });

        final User userObj = new User();
        userObj.setUserName(uname);

        final User.Profile profile = userObj.new Profile();
        profile.setFirstName(firstNameUser);
        profile.setLastName(lastnameUser);

        userService.createOrUpdateProfile(userObj, new App42CallBack() {
            public void onSuccess(Object response)
            {
                User user = (User)response;
                Log.d(TAG,"Firstname is:"+user.getProfile().getFirstName());
                Log.d(TAG,"Lastname is:"+user.getProfile().getLastName());
            }
            public void onException(Exception ex)
            {
                Log.d(TAG,"Exception Message"+ex.getMessage());

            }
        });
    }
}

