package com.promact.chatapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by grishma on 02-06-2017.
 */
public class GetAllUsersResponse implements Parcelable
{
    private String id;
    private String name;
    private String token;

    protected GetAllUsersResponse(Parcel in) {
        id = in.readString();
        name = in.readString();
        token = in.readString();
    }

    public static final Creator<GetAllUsersResponse> CREATOR = new Creator<GetAllUsersResponse>() {
        @Override
        public GetAllUsersResponse createFromParcel(Parcel in) {
            return new GetAllUsersResponse(in);
        }

        @Override
        public GetAllUsersResponse[] newArray(int size) {
            return new GetAllUsersResponse[size];
        }
    };

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(token);
    }
}
