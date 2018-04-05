package com.promact.chatapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by grishma on 05-06-2017.
 */
public class GetAllChatMsgsFromUserResponse implements Parcelable {
    private String id;
    private String message;
    private String fromUserId;
    private String toUserId;
    private String createdDateTime;

    protected GetAllChatMsgsFromUserResponse(Parcel in) {
        id = in.readString();
        message = in.readString();
        fromUserId = in.readString();
        toUserId = in.readString();
        createdDateTime = in.readString();
    }

    public static final Creator<GetAllChatMsgsFromUserResponse> CREATOR = new Creator<GetAllChatMsgsFromUserResponse>() {
        @Override
        public GetAllChatMsgsFromUserResponse createFromParcel(Parcel in) {
            return new GetAllChatMsgsFromUserResponse(in);
        }

        @Override
        public GetAllChatMsgsFromUserResponse[] newArray(int size) {
            return new GetAllChatMsgsFromUserResponse[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(message);
        parcel.writeString(fromUserId);
        parcel.writeString(toUserId);
        parcel.writeString(createdDateTime);
    }
}
