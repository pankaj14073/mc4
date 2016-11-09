package com.example.pankaj.todo_assignment;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by pankaj on 8/11/16.
 */

public class list_node implements Serializable {
    String title;
    String data;
    String date;

    public list_node(String title, String data)
    {
        this.title=title;
        this.data=data;
        this.date= DateFormat.getDateTimeInstance().format(new Date());
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

}
