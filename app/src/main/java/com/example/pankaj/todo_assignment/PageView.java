package com.example.pankaj.todo_assignment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PageView extends AppCompatActivity
{
    ViewPager viewPager;
    ArrayList<list_node> contactsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Page View ");
       ab.setDisplayHomeAsUpEnabled(true);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3dc8ff")));
        PageHandler handler=new PageHandler(getApplicationContext());
        contactsList=new ArrayList<list_node>();
        contactsList= handler.getAllHistory();
        PageViewAdapter adapter = new PageViewAdapter(getSupportFragmentManager(), contactsList);
         viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

}
