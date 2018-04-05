package com.promact.apirequest;

import java.util.ArrayList;

/**
 * Created by grishma on 19-09-2016.
 */
public interface DownloadJsonString<T>
{
    public void onTaskComplete(ArrayList<Info> serverArrayList);
}
