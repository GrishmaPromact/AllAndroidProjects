package com.promact.youtubedemoapp;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by grishma on 01-03-2017.
 */
public class CategoryResponse
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

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "kind='" + kind + '\'' +
                ", etag='" + etag + '\'' +
                ", items=" + items +
                '}';
    }

    public class Items implements Parcelable {
        private String kind;
        private String etag;
        private String id;
        private Snippet snippet;

        protected Items(Parcel in) {
            kind = in.readString();
            etag = in.readString();
            id = in.readString();
        }

        public final Creator<Items> CREATOR = new Creator<Items>() {
            @Override
            public Items createFromParcel(Parcel in) {
                return new Items(in);
            }

            @Override
            public Items[] newArray(int size) {
                return new Items[size];
            }
        };

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }


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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(kind);
            parcel.writeString(etag);
            parcel.writeString(id);
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
}
