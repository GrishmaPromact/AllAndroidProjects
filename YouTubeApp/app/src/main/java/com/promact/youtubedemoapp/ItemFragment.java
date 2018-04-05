package com.promact.youtubedemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ItemFragment extends Fragment {

    private CategoryChannelsResponse.Items1 categoryChannelsResponse;
    private OnFragmentInteractionListener mListener;
    private ArrayList<ChannelsItemResponse.Items> channelItems = new ArrayList<>();
    boolean isScrollingDown = false;
    private ImageButton imageButton;
    private Context context;
    private String nextPageTokenn;
    private RecyclerView mRecyclerView;                           // Declaring RecyclerView
    private UserAdapter mAdapter;
    private String token;
    private View view;
    // Declaring Adapter For Recycler View
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static ItemFragment newInstance(CategoryChannelsResponse.Items1 items1, String token) {

        ItemFragment itemFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", items1);
        args.putString("accesstoken",token);
        itemFragment.setArguments(args);
        return itemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryChannelsResponse = getArguments().getParcelable("item");
            token=getArguments().getString("accesstoken");
            Log.d("category item response:" , categoryChannelsResponse.getId());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_item, container, false);
        context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.grid);
        imageButton=(ImageButton)view.findViewById(R.id.utube_icon);
        // Assigning the RecyclerView Object to the xml View
       // swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UserAdapter(getActivity(),channelItems,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
       /* footer = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loading_view, null, false);
        mRecyclerView.addFooterView(footer);
*/

        String response;
        try {
            response = new GetResponseFromChannelsItemsTask(getActivity(), token,categoryChannelsResponse).execute().get();
            Gson gson = new GsonBuilder().create();
            ChannelsItemResponse categoriesChannels = gson.fromJson(response, ChannelsItemResponse.class);
            nextPageTokenn=categoriesChannels.getNextPageToken();
            System.out.println("nextpagetoken::"+nextPageTokenn);//("nextpagetoken::",nextPageTokenn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                additems();
            }

            private void additems()
            {
                isScrollingDown = true;
                if (nextPageTokenn == null) {
                    isScrollingDown = false;
                } else {
                    String response;
                    try {
                        response = String.valueOf(new GetResponseFromChannelsItemsNextPageTask(getActivity(), categoryChannelsResponse, nextPageTokenn).execute().get());
                        Gson gson = new GsonBuilder().create();
                        ChannelsItemResponse categoriesChannels = gson.fromJson(response, ChannelsItemResponse.class);
                        nextPageTokenn = categoriesChannels.getNextPageToken();
                        System.out.println("nextpagetoken::" + nextPageTokenn);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    isScrollingDown = false;
                }
            }
        });
*/

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More");


                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        additems();
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setLoaded();
                    }

                    private void additems()
                    {
                      /*  isScrollingDown = true;*/
                        if (nextPageTokenn == null) {
                           /* isScrollingDown = false;*/
                        } else {
                            String response;
                            try {
                                response = String.valueOf(new GetResponseFromChannelsItemsNextPageTask(getActivity(),token, categoryChannelsResponse, nextPageTokenn).execute().get());
                                Gson gson = new GsonBuilder().create();
                                ChannelsItemResponse categoriesChannels = gson.fromJson(response, ChannelsItemResponse.class);
                                nextPageTokenn = categoriesChannels.getNextPageToken();
                                System.out.println("nextpagetoken::" + nextPageTokenn);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            /*isScrollingDown = false;*/
                        }
                    }
                }, 5000);
            }
        });

       /* imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

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
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    Bundle b = new Bundle();
                    b.putString("key", (channelItems.get(position).getId().getVideoId())); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
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
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public boolean allowBackPressed()
    {
        return true;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    public class GetResponseFromChannelsItemsTask extends AsyncTask<String,String,String> {

        private Context mContext;
        private CategoryChannelsResponse.Items1 items;
        private Activity context;
        private String token;

        public GetResponseFromChannelsItemsTask(Context context,String token, CategoryChannelsResponse.Items1 items) {
            mContext = context;
            this.items=items;
            this.token=token;
        }
       /* protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Getting your data... Please wait...");
            dialog.show();
        }*/

        @Override
        protected String doInBackground(String... strings)
        {
            String response="";
            try {
                response=new ApiClient().sendGetForChannelsItem(items.getId(),token);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        @Override
        protected void onPostExecute(String response) {
            //dialog.dismiss();
            Log.d("----------response-----",response);
            Gson gson = new GsonBuilder().create();
            ChannelsItemResponse categoriesChannels = gson.fromJson(response, ChannelsItemResponse.class);
            channelItems.addAll(categoriesChannels.getItems());
            mAdapter.notifyDataSetChanged();
        }
    }
    public class GetResponseFromChannelsItemsNextPageTask extends AsyncTask<String,String,String> {

        private Context mContext;
        private CategoryChannelsResponse.Items1 items;
        private String nextpageToken;
        private String token;
        public GetResponseFromChannelsItemsNextPageTask(Context context,String token, CategoryChannelsResponse.Items1 items, String nextpageToken) {
            mContext = context;
            this.items=items;
            this.nextpageToken=nextpageToken;
            this.token=token;
        }
        @Override
        protected String doInBackground(String... strings)
        {
          /*  isScrollingDown = true;*/
            String response="";
            try {
                response=new ApiClient().sendGetForChannelsItemNextPage(items.getId(),nextpageToken,token);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        @Override
        protected void onPostExecute(String response) {
            Log.d("----------response-----",response);
            Gson gson = new GsonBuilder().create();
            ChannelsItemResponse categoriesChannels = gson.fromJson(response, ChannelsItemResponse.class);
            channelItems.addAll(categoriesChannels.getItems());
            mAdapter.notifyDataSetChanged();
        }
    }
}
