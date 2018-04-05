package com.promact.youtubedemoapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BestOfUtubeFragment extends Fragment
{
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private static BestOfUtubeFragment fragment;
    private int firstVisibleItem, visibleItemCount,totalItemCount,expandapleInt;
    boolean isScrollingDown = false;
    private OnFragmentInteractionListener mListener;
    private View view;
    private ListAdapter adapter;
    private ListView listView;
    private String TAG = "BestOfUtubeFragment";
    private View footer;
    private String nextPageTokenn;
    private String token;
    private CategoryResponse.Items categoryItemResponse;
    private ArrayList<CategoryChannelsResponse.Items1> categoryChannelItems = new ArrayList<>();

    public static BestOfUtubeFragment newInstance(CategoryResponse.Items item, String accessToken) {
        fragment = new BestOfUtubeFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        args.putString("accessToken",accessToken);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryItemResponse = getArguments().getParcelable("item");
            token=getArguments().getString("accessToken");
            System.out.println("Token  in fragment is:"+token);
            Log.d("category item response:" , categoryItemResponse.getId());
           /* FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.disallowAddToBackStack();*/

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_best_of_utube, container, false);
        adapter = new FragmentListAdapter(getActivity().getApplicationContext(),
                R.layout.listview_layout, categoryChannelItems);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // Add footer view
        footer = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loading_view, null, false);
        listView.addFooterView(footer);
        //String new GetResponseFromCategoryItemsTask(getActivity(), categoryItemResponse).execute();
        String response;
        try {
            response = new GetResponseFromCategoryItemsTask(getActivity(),token, categoryItemResponse).execute().get();
            Gson gson = new GsonBuilder().create();
            CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
            nextPageTokenn=categoriesChannels.getNextPageToken();
            System.out.println("nextpagetoken::"+nextPageTokenn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
         /* AsyncTask getResponseFromCategoryItemsTask = new GetResponseFromCategoryItemsTask(getActivity(),
                categoryItemResponse, new JsonResponseCategory<ArrayList<CategoryChannelsResponse>>() {
            @Override
            public void onTaskComplete(CategoryChannelsResponse categoryChannelsResponse) {
                nextPageToken = categoryChannelsResponse.getNextPageToken();
                Log.d("next page token is:" , nextPageToken);
                categoryChannelItems.addAll(categoryChannelsResponse.getItems());

                ((ArrayAdapter<CategoryChannelsResponse.Items1>) adapter).notifyDataSetChanged();
                if (nextPageToken != null) {
                    new GetResponseFromCategoryItemsNextPageTask(getActivity(), categoryItemResponse, new JsonResponseCategory<ArrayList<CategoryChannelsResponse>>() {
                        @Override
                        public void onTaskComplete(CategoryChannelsResponse serverArrayList) {
                            categoryChannelItems.addAll(serverArrayList.getItems());
                            ((ArrayAdapter<CategoryChannelsResponse.Items1>) adapter).notifyDataSetChanged();

                        }
                    }, nextPageToken).execute();
                }
            }
        }).execute();*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                ItemFragment itemFragment = ItemFragment.newInstance(categoryChannelItems.get(position),token);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.hide(fragment);
                ft.add(R.id.frameLayout, itemFragment); // f2_container is your FrameLayout container
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                int lastInScreen = firstVisibleItem + visibleItemCount;
                if((lastInScreen == totalItemCount) && !(isScrollingDown)){
                   additems();
                }
            }
            private void additems() {
                isScrollingDown=true;
                if(nextPageTokenn==null)
                    {
                        listView.removeFooterView(footer);
                        isScrollingDown=false;
                    }
                    else
                    {
                        String response;
                        try {
                            response= String.valueOf(new GetResponseFromCategoryItemsNextPageTask(getActivity(), token,categoryItemResponse, nextPageTokenn).execute().get());
                            Gson gson = new GsonBuilder().create();
                            CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
                            nextPageTokenn=categoriesChannels.getNextPageToken();
                            System.out.println("nextpagetoken::"+nextPageTokenn);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        isScrollingDown=false;
                    }
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

        void onConnectionFailed(ConnectionResult connectionResult);
    }
    public class GetResponseFromCategoryItemsTask extends AsyncTask<String,String,String> {

        private Context mContext;
        private CategoryResponse.Items items;
        private Activity context;
        private String token;
        public GetResponseFromCategoryItemsTask(Context context,String token, CategoryResponse.Items items) {
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
                response=new ApiClient().sendGetForChannels(items.getId(),token);
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
            CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
            categoryChannelItems.addAll(categoriesChannels.getItems());
            ((ArrayAdapter<CategoryChannelsResponse.Items1>) adapter).notifyDataSetChanged();
          }
    }

    public class GetResponseFromCategoryItemsNextPageTask extends AsyncTask<String,String,String> {

        private Context mContext;
        private CategoryResponse.Items items;
        private String nextpageToken;
        private String token;
        public GetResponseFromCategoryItemsNextPageTask(Context context,String token, CategoryResponse.Items items, String nextpageToken) {
            mContext = context;
            this.items=items;
            this.nextpageToken=nextpageToken;
            this.token=token;
        }
        @Override
        protected String doInBackground(String... strings)
        {
            isScrollingDown = true;
            String response="";
            try {
                response=new ApiClient().sendGetForChannelsNextPage(items.getId(),nextpageToken,token);
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
            CategoryChannelsResponse categoriesChannels = gson.fromJson(response, CategoryChannelsResponse.class);
            categoryChannelItems.addAll(categoriesChannels.getItems());
            ((ArrayAdapter<CategoryChannelsResponse.Items1>) adapter).notifyDataSetChanged();
        }
    }
}



