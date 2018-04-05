package com.promact.youtubeapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishma on 19-09-2016.
 */
public interface DownloadJsonString<T>
{
    public void onTaskComplete(ArrayList<String> serverArrayList);
}
