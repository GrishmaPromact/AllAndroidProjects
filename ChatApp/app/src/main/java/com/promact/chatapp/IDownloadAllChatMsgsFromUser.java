package com.promact.chatapp;

import java.util.List;

/**
 * Created by grishma on 05-06-2017.
 */
public interface IDownloadAllChatMsgsFromUser<T> {
    public void onTaskComplete(List<GetAllChatMsgsFromUserResponse> serverArrayList);
}
