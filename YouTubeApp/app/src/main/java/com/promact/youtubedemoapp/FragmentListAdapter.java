package com.promact.youtubedemoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by grishma on 03-08-2016.
 */
public class FragmentListAdapter extends ArrayAdapter<CategoryChannelsResponse.Items1>
{
    private Context context;
    ArrayList<CategoryChannelsResponse>items;
    public FragmentListAdapter(Context context, int resource, ArrayList<CategoryChannelsResponse.Items1> items) {
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
        CategoryChannelsResponse.Items1 categoryChannelsResponse = getItem(position);
        if (categoryChannelsResponse != null) {
            ImageView iconCatChannel = (ImageView) view.findViewById(R.id.cat_channel);
            TextView catTitle = (TextView) view.findViewById(R.id.channel_title);
            catTitle.setText(categoryChannelsResponse.getSnippet().getTitle());
            //imageLoader.DisplayImage(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl(), loader, iconCatChannel);
            Picasso.with(context).load(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl()).into(iconCatChannel);
            if (catTitle.getText().equals("")) {
                catTitle.setVisibility(View.GONE);
            } else {
                catTitle.setVisibility(View.VISIBLE);
            }
        }
        //imageLoader.DisplayImage(categoryChannelsResponse.getSnippet().getThumbnails().getMedium().getUrl(), loader, iconCatChannel);
        return view;
    }
}