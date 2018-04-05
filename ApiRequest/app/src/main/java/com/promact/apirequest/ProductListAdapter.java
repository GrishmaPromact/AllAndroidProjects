package com.promact.apirequest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by grishma on 03-08-2016.
 */
public class ProductListAdapter extends ArrayAdapter<Info>
{
    private Context context;
    public ProductListAdapter(Context context, int resource, List<Info> items) {
        super(context, resource, items);
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
        Info info = getItem(position);
        if (info != null) {

            TextView idTextView = (TextView) view.findViewById(R.id.id);

            if (idTextView != null) {
                idTextView.setText(info.getId());
                if (idTextView.getText().equals("")) {
                    idTextView.setVisibility(View.GONE);
                } else {
                    idTextView.setVisibility(View.VISIBLE);
                }
            }

            TextView titleTextView = (TextView) view.findViewById(R.id.title);

            if (titleTextView != null) {
                titleTextView.setText(info.getTitle());
                if (titleTextView.getText().equals("")) {
                    titleTextView.setVisibility(View.GONE);
                } else {
                    titleTextView.setVisibility(View.VISIBLE);
                }
            }

            TextView descTextView = (TextView) view.findViewById(R.id.description);

            if (descTextView != null) {
                descTextView.setText(info.getDescription());
                if (descTextView.getText().equals("")) {
                    descTextView.setVisibility(View.GONE);
                } else {
                    descTextView.setVisibility(View.VISIBLE);
                }
            }
        }
        return view;
    }
}