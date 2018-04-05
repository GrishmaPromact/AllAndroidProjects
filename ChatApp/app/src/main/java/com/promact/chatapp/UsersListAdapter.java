package com.promact.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


 // Created by grishma on 02-06-2017.


public class UsersListAdapter extends ArrayAdapter<String>
{
    private Context context;
    ArrayList<GetAllUsersResponse> items;
    public UsersListAdapter(Context context, int resource, ArrayList<String > items) {
        super(context,resource,items);
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.listview_layout,null);
        }
       String  getAllUsersResponse = getItem(position);
        if (getAllUsersResponse != null) {
            TextView usersName = (TextView) view.findViewById(R.id.noUsersText);
            usersName.setText(getAllUsersResponse);
            //imageLoader.DisplayImage(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl(), loader, iconCatChannel);
            //Picasso.with(context).load(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl()).into(iconCatChannel);
            if (usersName.getText().equals("")) {
                usersName.setVisibility(View.GONE);
            } else {
                usersName.setVisibility(View.VISIBLE);
            }
        }
        //imageLoader.DisplayImage(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl(), loader, iconCatChannel);
        return view;
    }
}
