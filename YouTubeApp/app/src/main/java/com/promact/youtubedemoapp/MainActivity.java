package com.promact.youtubedemoapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "TAG_FRAGMENT";
    private String NAME = "Grishma Ukani";
    private String EMAIL = "grishma@promactinfo.com";
    private int PROFILE=R.drawable.grishma;

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private RecyclerView mRecyclerView;                           // Declaring RecyclerView
    private RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    private RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    private DrawerLayout Drawer;
    private ActionBar actionBar;
    private AccessToken aceessToken;
    private String accessToken;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;
    private BestOfUtubeFragment bestOfUtubeFragment;
    private ImageLoader imageLoader;
    private int RC_SIGN_IN = 100;
    private boolean isLogin = false;
    //google api client

    private GoogleApiClient mGoogleApiClient;
    //Signing Options
    private GoogleSignInOptions gso;
    private ArrayList<CategoryResponse.Items> items = new ArrayList<>();

    // Declaring Action Bar Drawer Toggle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //signIn();
        sharedPreferences = getSharedPreferences("nextpagetokennn", Context.MODE_PRIVATE);
        ArrayList<CategoryResponse.Items> category = new CategoryResponse().getItems();
      /*  if (bestOfUtubeFragment == null) {
            fragment = bestOfUtubeFragment.newInstance(category,p);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }*/
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("YouTube");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);
        CircleImageView pic = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.circleView);

       /* final AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<CategoryResponse>>() {
            @Override
            public void onTaskComplete(CategoryResponse categoryResponse) {
                items.addAll(categoryResponse.getItems());
                String title = "";
                ArrayList<String> titles = new ArrayList<String>();
                for (int i = 0; i < items.size(); i++) {

                    titles.add(items.get(i).getSnippet().getTitle());
                }

                mAdapter = new MyAdapter(getApplicationContext(), titles, NAME, EMAIL, PROFILE);
                mRecyclerView.setAdapter(mAdapter);

            }
        }).execute();*/
          /* *//* ArrayList<String>titles=new ArrayList<String>();
            @Override
            public ArrayList<String> onTaskComplete(ArrayList<String> serverArrayList) {
                for (int i = 0; i <serverArrayList.size() ; i++)
                {
                    String title="";
                    title=serverArrayList.get(i).new Snippet().getTitle();
                    titles.add(title);
                }*//**//*
              *//**//*  mAdapter = new MyAdapter(titles,NAME,EMAIL,PROFILE);
                mRecyclerView.setAdapter(mAdapter);
*//*
            // Setting the adapter to RecyclerView;
            // return titles;

        }).execute();
*/
     /*   final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());

                if(child!=null && mGestureDetector.onTouchEvent(e)){

                    final int position = mRecyclerView.getChildAdapterPosition(child);
                    //ArrayList<CategoryResponse.Items> categoryitems=new CategoryResponse().getItems();
                    fragment = BestOfUtubeFragment.newInstance(items.get(position-1));
                    Log.d("KMH", String.valueOf(position-1));
                    // Insert the fragment by replacing any existing fragment
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolbar.setTitle(items.get(position-1).getSnippet().getTitle());
                    Drawer.closeDrawers();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
        // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
                // Drawer.closeDrawer(GravityCompat.START);
            }
        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           // sharedPreferences.edit().putBoolean("isLogin", false).apply();
            sharedPreferences.edit().clear().apply();

            Intent i=new Intent(MainActivity.this,LoginYoutubeActivity.class);
                    /*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

       /* Drawer.closeDrawer(GravityCompat.START);
        return true;*/
    }

    /*  private void updateUI(boolean signedIn)
      {
          if (signedIn) {
              System.out.println("-----HELLO---------");
              sharedPreferences = getSharedPreferences("hello", Context.MODE_PRIVATE);
              sharedPreferences.edit().clear().apply();
              Intent i=new Intent(getApplicationContext(),LoginYoutubeActivity.class);
              startActivity(i);
              finish();
          }
      }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        super.onBackPressed();


    }

    /* @Override
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
              NAME = acct.getDisplayName();
              EMAIL=acct.getEmail();
              PROFILE= acct.getPhotoUrl();
             String token=acct.getIdToken();
             System.out.println("token is:"+token);
             System.out.println(NAME);
             System.out.println(PROFILE);




            *//* Intent i=new Intent(getApplicationContext(),ProfileInformationActivity.class);
            startActivity(i);*//*

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume");

        if (getIntent() != null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = getIntent().getData();
            String code = uri.getQueryParameter("code");
            Log.d("AUTH CODE:", code);
            new TokenGet(code).execute();

        }

    }


    private class TokenGet extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        private String Code;
        private Context mContext;
        private CategoryResponse.Items items;
        private Activity context;

        public TokenGet(String Code) {
            // mContext = context;
            this.Code = Code;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Contacting Google ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
               /* Code = sharedPreferences.getString("Code", "");*/
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            String response = "";
            try {
                response = new ApiClient().sendPostRequestToOAuthServer(Code);
                Log.d("responseeeeeeeeeeeeee", response);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }

        @Override
        protected void onPostExecute(String downloadString) {
            pDialog.dismiss();
            Log.d("responseeee:", downloadString);
            Gson gson = new GsonBuilder().create();
            AccessToken token = gson.fromJson(downloadString, AccessToken.class);
            String accessToken = token.getAccess_token();
            Log.d("token:", accessToken);
            sendRequest(accessToken);
        }
    }

    private void sendRequest(final String accessToken)
    {
          final AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(),accessToken, new DownloadJsonString<ArrayList<CategoryResponse>>() {
            @Override
            public void onTaskComplete(CategoryResponse categoryResponse) {
                items.addAll(categoryResponse.getItems());
                String title = "";
                ArrayList<String> titles = new ArrayList<String>();
                for (int i = 0; i < items.size(); i++) {

                    titles.add(items.get(i).getSnippet().getTitle());
                }

                mAdapter = new MyAdapter(getApplicationContext(), titles, NAME, EMAIL, PROFILE);
                mRecyclerView.setAdapter(mAdapter);

            }
        }).execute();



        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());

                if(child!=null && mGestureDetector.onTouchEvent(e)){

                    final int position = mRecyclerView.getChildAdapterPosition(child);
                    //ArrayList<CategoryResponse.Items> categoryitems=new CategoryResponse().getItems();
                    fragment = BestOfUtubeFragment.newInstance(items.get(position-1),accessToken);
                    Log.d("KMH", String.valueOf(position-1));
                    // Insert the fragment by replacing any existing fragment
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolbar.setTitle(items.get(position-1).getSnippet().getTitle());
                    Drawer.closeDrawers();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


}

