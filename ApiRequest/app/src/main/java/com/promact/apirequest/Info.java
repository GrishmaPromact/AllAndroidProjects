package com.promact.apirequest;

import java.util.ArrayList;

/**
 * Created by grishma on 02-08-2016.
 */
public class Info {
    private String id;
    private String title;
    private String description;

    public Info() {
    }

    public Info(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
