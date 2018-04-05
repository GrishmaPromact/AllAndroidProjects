package com.promact.chatapp;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by grishma on 05-06-2017.
 */
public class ChatActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ImageView sendButton;
    private EditText messageArea;
    private ScrollView scrollView;
    private static final String TAG="ChatActivity";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String userId;
    private String userIdLogin;
    private String authToken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent i=getIntent();
        userId=  i.getExtras().getString("userId");
        userIdLogin=i.getExtras().getString("userIdLogin");
        authToken=i.getExtras().getString("authToken");
        Log.d(TAG,"intent get user id from user activity is::"+userId);
        Log.d(TAG,"intent get login user id from user activity is::"+userIdLogin);
        Log.d(TAG,"intent get user auth token from user activity is::"+authToken);


        doSomethingRepeatedly();
       /* new getAllChatMsgsFromUser(userId, authToken, new IDownloadAllChatMsgsFromUser<GetAllChatMsgsFromUserResponse>() {
            @Override
            public void onTaskComplete(List<GetAllChatMsgsFromUserResponse> getAllChatMsgsFromUserResponseList) {
                for (int i = 0; i <getAllChatMsgsFromUserResponseList.size() ; i++) {
                    String msg=getAllChatMsgsFromUserResponseList.get(i).getMessage();
                    String fromUserId=getAllChatMsgsFromUserResponseList.get(i).getFromUserId();
                    Log.d(TAG,"messge from users::"+msg);
                    Log.d(TAG,"from user id::"+fromUserId);
                    addReceiverMsgBox(msg,2,fromUserId);
                }
            }
        }).execute();*/
        sharedPreferences = getSharedPreferences("chatmsg", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        linearLayout = (LinearLayout)findViewById(R.id.layout1);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ChatApp");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(UserDetails.chatWith);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    Log.d("username:",UserDetails.username);
                    Log.d("Message",messageText);
                    new SendMsgToUser(authToken,userId,messageText).execute();
                }
                messageArea.setText("");
            }
        });
        messageArea.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            String messageText = messageArea.getText().toString();

                            if(!messageText.equals("")){
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("message", messageText);
                                map.put("user", UserDetails.username);
                                Log.d("username:",UserDetails.username);
                                Log.d("Messgae",messageText);
                                new SendMsgToUser(authToken,userId,messageText).execute();
                            }
                            messageArea.setText("");
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
        LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16,10,16,10);
        layoutParams.gravity=Gravity.RIGHT;
        //lp.setMargins(10,0,20,0);
        textView.setLayoutParams(layoutParams);
        Log.d("CHAT","========================================");
        Log.d("CHAT",message);
        Log.d("CHAT","========================================");

        final ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

        ComponentName componentInfo = taskInfo.get(0).topActivity;
        if (componentInfo.getPackageName().equalsIgnoreCase("com.promact.chatapp")) {

        } else {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0  , intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("ChatApp")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 , notificationBuilder.build());
        }

        if(type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            textView.setBackgroundResource(R.drawable.rounded_corner2);
        }

        linearLayout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
    private void addReceiverMsgBox(String message,int type,String fromuserId)
    {
        //doSomethingRepeatedly();
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        textView.setTextColor(getResources().getColor(R.color.black));

        if(userIdLogin.equals(fromuserId)) {
            textView.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(16, 10, 16, 10);
            lp.gravity = Gravity.RIGHT;
            //lp.setMargins(10,0,20,0);
            textView.setLayoutParams(lp);
        }
        else
        {
            textView.setGravity(Gravity.LEFT | Gravity.BOTTOM);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(16, 10, 16, 10);
            lp.gravity = Gravity.LEFT;
            //lp.setMargins(10,0,20,0);
            textView.setLayoutParams(lp);
        }

        Log.d("CHAT","========================================");
        Log.d("CHAT",message);
        Log.d("CHAT","========================================");

        final ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

        ComponentName componentInfo = taskInfo.get(0).topActivity;
        if (componentInfo.getPackageName().equalsIgnoreCase("com.promact.chatapp")) {

        } else {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0  , intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("ChatApp")
                    .setContentText( message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 , notificationBuilder.build());
        }

        if(type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            textView.setBackgroundResource(R.drawable.rounded_corner2);
        }

        linearLayout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
    public class getAllChatMsgsFromUser extends AsyncTask<String,String,String>
    {
        private String userId;
        private String authToken;
        private ProgressDialog progressDialog;
        private IDownloadAllChatMsgsFromUser<GetAllChatMsgsFromUserResponse>callback;
        getAllChatMsgsFromUser(String userId,String authToken,IDownloadAllChatMsgsFromUser<GetAllChatMsgsFromUserResponse>cb)
        {
            this.userId=userId;
            this.authToken=authToken;
            this.callback=cb;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ChatActivity.this);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String downloadAllChatMsgsResponseOfAllUsers="";
            try{
                downloadAllChatMsgsResponseOfAllUsers=  new ApiClient().getAllChatMsgsWithUser(userId,authToken);
                Log.d(TAG,"Download json string response of all users is:"+downloadAllChatMsgsResponseOfAllUsers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return downloadAllChatMsgsResponseOfAllUsers;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            progressDialog.dismiss();
            Gson gson=new Gson();
            GetAllChatMsgsFromUserResponse[] getAllChatMsgsFromUsersResponse = gson.fromJson(response, GetAllChatMsgsFromUserResponse[].class);
            Type listType = new TypeToken<List<GetAllChatMsgsFromUserResponse>>(){}.getType();
            List<GetAllChatMsgsFromUserResponse> posts = (List<GetAllChatMsgsFromUserResponse>) gson.fromJson(response, listType);
            callback.onTaskComplete(posts);
        }
    }
    public class SendMsgToUser extends AsyncTask<String,String,String>
    {
        private String authToken;
        private String userId;
        private String msgText;
        private ProgressDialog progressDialog;
        SendMsgToUser(String authToken, String userId, String messageText)
        {
            this.authToken=authToken;
            this.userId=userId;
            this.msgText=messageText;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ChatActivity.this);
            progressDialog.setMessage("Please wait ...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String sendChatMsgToUser="";
            try{
                sendChatMsgToUser=  new ApiClient().sendPostReqToSendMsg(authToken,userId,msgText);
                Log.d(TAG,"Download json string response of all users is:"+sendChatMsgToUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sendChatMsgToUser;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            //doSomethingRepeatedly();
            addMessageBox(msgText,1);
        }
    }
    private void doSomethingRepeatedly() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {

                try{
                    Log.e(TAG,"User id in method is:"+userId);
                    Log.e(TAG,"User authtoken in method is:"+authToken);
                    new getAllChatMsgsFromUser(userId, authToken, new IDownloadAllChatMsgsFromUser<GetAllChatMsgsFromUserResponse>() {
                        @Override
                        public void onTaskComplete(List<GetAllChatMsgsFromUserResponse> getAllChatMsgsFromUserResponseList) {
                            for (int i = 0; i <getAllChatMsgsFromUserResponseList.size() ; i++) {
                                String msg=getAllChatMsgsFromUserResponseList.get(i).getMessage();
                                String fromUserId=getAllChatMsgsFromUserResponseList.get(i).getFromUserId();
                                Log.d(TAG,"messge from users::"+msg);
                                Log.d(TAG,"from user id::"+fromUserId);
                                addReceiverMsgBox(msg,2,fromUserId);
                            }
                        }
                    }).execute();
                }
                catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }, 0, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //doSomethingRepeatedly();
    }
    /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(getApplicationContext(),UsersActivity.class);
        startActivity(i);
        finish();
    }*/
}
