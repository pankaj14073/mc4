package com.example.pankaj.todo_assignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pankaj on 9/11/16.
 */

public class PageFragment extends Fragment {

    public static PageFragment newInstance(list_node singleContact)
    {

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
      /*  bundle.putString("name", singleContact.getName());
        bundle.putString("phone", singleContact.getPhone());
        pageFragment.setArguments(bundle);*/

        bundle.putSerializable("node", singleContact);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_node, container, false);
        final TextView textView1 = (TextView) view.findViewById(R.id.tittle);
        final TextView textView2 = (TextView) view.findViewById(R.id.data);
        final TextView textView3 = (TextView) view.findViewById(R.id.date);

        list_node cont= (list_node) getArguments().getSerializable("node");

        textView1.setText(cont.title);
        textView2.setText(cont.data);
        textView3.setText(cont.date);

        return view;
    }
}
