package com.promact.youtubeapp;
import android.widget.ListView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 01-03-2017.
 */
public class Category
{
    private String kind;
    private String etag;
    private ArrayList<Items>items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public class Items
    {
        private String kind;
        private String etag;
        private String id;
        private Snippet snippet;
       /* private ArrayList<String>titles;
        private String channelId;
        private String title;*/

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public class Snippet
        {
            private String channelId;
            private String title;

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }

    }
    public ArrayList<String> getTitles(ArrayList<Items>items)
    {
        String title="";
        ArrayList<String>titles=new ArrayList<>();
        for (int i = 0; i <items.size() ; i++) {
            title=items.get(i).snippet.getTitle();
            titles.add(title);
        }
        return titles;
    }
}
