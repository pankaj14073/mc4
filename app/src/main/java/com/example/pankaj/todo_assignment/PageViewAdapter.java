package com.example.pankaj.todo_assignment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by pankaj on 9/11/16.
 */

public  class PageViewAdapter extends FragmentPagerAdapter {

    ArrayList<list_node> users;

    public PageViewAdapter(FragmentManager fm, ArrayList<list_node> usersList) {
        super(fm);
        this.users=usersList;
    }

    @Override
    public Fragment getItem(int index) {

        return PageFragment.newInstance(users.get(index));
    }

    @Override
    public int getCount() {

        return users.size();
    }
}
