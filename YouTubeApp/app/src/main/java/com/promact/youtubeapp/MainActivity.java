package com.promact.youtubeapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see
   // String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
   // int ICONS[] = {R.drawable.ic_home,R.drawable.ic_events,R.drawable.ic_mail,R.drawable.ic_shop,R.drawable.ic_travel};
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view
    String NAME = "Grishma Ukani";
    String EMAIL = "grishma@promactinfo.com";
    int PROFILE = R.drawable.grishma;
    private Toolbar toolbar;                              // Declaring the Toolbar Object
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);
        CircleImageView pic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.circleView);
        AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Category>>() {
            @Override
            public void onTaskComplete(ArrayList<String> serverArrayList) {

                mAdapter = new MyAdapter(serverArrayList,NAME,EMAIL,PROFILE);
                mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

            }
        }).execute();
        // Letting the system know that the list objects are of fixed size
            // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
      //  mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment = null;
    private String accessToken;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       */
/* if (itemFragment == null) {
            fragment = ItemFragment.newInstance(2);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
*//*

        toolbar.setTitleTextColor(Color.WHITE);
        actionBar = getSupportActionBar();
        actionBar.setTitle("YouTube");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // add NavigationItemSelectedListener to check the navigation clicks
        navigationView.setNavigationItemSelectedListener(this);
        AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Category>>() {
            @Override
            public void onTaskComplete(ArrayList<String> serverArrayList) {

                Menu menu = navigationView.getMenu();
                MenuItem best_of_utube = menu.findItem(R.id.best_of_utube);
                best_of_utube.setTitle(serverArrayList.get(0).toString());

                MenuItem recmnd_foru = menu.findItem(R.id.recmnd_foru);
                recmnd_foru.setTitle(serverArrayList.get(1).toString());

                MenuItem paid_chnls = menu.findItem(R.id.paid_chnls);
                paid_chnls.setTitle(serverArrayList.get(2).toString());

                MenuItem music = menu.findItem(R.id.music);
                music.setTitle(serverArrayList.get(3).toString());

                MenuItem comedy = menu.findItem(R.id.comedy);
                comedy.setTitle(serverArrayList.get(4).toString());

                MenuItem film_entrtnmnt = menu.findItem(R.id.film_entrtnmnt);
                film_entrtnmnt.setTitle(serverArrayList.get(5).toString());

                MenuItem gaming = menu.findItem(R.id.gaming);
                gaming.setTitle(serverArrayList.get(6).toString());

                MenuItem beauty_fsn = menu.findItem(R.id.beauty_fsn);
                beauty_fsn.setTitle(serverArrayList.get(7).toString());

                MenuItem from_tv = menu.findItem(R.id.from_tv);
                from_tv.setTitle(serverArrayList.get(8).toString());

                MenuItem automotive = menu.findItem(R.id.automotive);
                automotive.setTitle(serverArrayList.get(9).toString());

                MenuItem top_utube_cltns = menu.findItem(R.id.top_utube_cltns);
                top_utube_cltns.setTitle(serverArrayList.get(10).toString());

                MenuItem animation = menu.findItem(R.id.animation);
                animation.setTitle(serverArrayList.get(11).toString());

                MenuItem sports = menu.findItem(R.id.sports);
                sports.setTitle(serverArrayList.get(12).toString());

                MenuItem howto_diy = menu.findItem(R.id.howto_diy);
                howto_diy.setTitle(serverArrayList.get(13).toString());

                MenuItem tech = menu.findItem(R.id.tech);
                tech.setTitle(serverArrayList.get(14).toString());

                MenuItem sci_edu = menu.findItem(R.id.sci_edu);
                sci_edu.setTitle(serverArrayList.get(15).toString());

                MenuItem cooking_health = menu.findItem(R.id.cooking_health);
                cooking_health.setTitle(serverArrayList.get(16).toString());

                MenuItem causes_nonprofits = menu.findItem(R.id.causes_nonprofits);
                causes_nonprofits.setTitle(serverArrayList.get(17).toString());

                MenuItem news_politics = menu.findItem(R.id.news_politics);
                news_politics.setTitle(serverArrayList.get(18).toString());

                MenuItem lifestyle = menu.findItem(R.id.lifestyle);
                lifestyle.setTitle(serverArrayList.get(19).toString());

            }
        }).execute();
    }
   */
/* AsyncTask getResponseFromServerTask = new GetResponseFromServerTask(getBaseContext(), new DownloadJsonString<ArrayList<Category>>() {
        @Override
        public void onTaskComplete(ArrayList<String> serverArrayList) {
            *//*
*/
/*for (int i = 0; i <serverArrayList.size() ; i++) {
               String title= serverArrayList.get(i).toString();
            }*//*
*/
/*

        }
    }).execute();*//*

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds Category to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       */
/* if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*//*
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
*/
