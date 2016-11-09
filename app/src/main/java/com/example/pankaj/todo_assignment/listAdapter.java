package com.example.pankaj.todo_assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankaj on 8/11/16.
 */

public class listAdapter 
        extends RecyclerView.Adapter
        <listAdapter .ListItemViewHolder> {

    private ArrayList<list_node> items;
    private SparseBooleanArray selectedItems;
    private Context context;


    listAdapter(ArrayList<list_node> modelData,Context context) {
        if (modelData == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        this.context=context;
        items = modelData;
        selectedItems = new SparseBooleanArray();
    }

    listAdapter() {
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
        list_node model = items.get(position);
        final int k = position;
        viewHolder.tittle.setText(String.valueOf(model.getTitle()));
        viewHolder.data.setText(String.valueOf(model.getData()));
        viewHolder.date.setText(String.valueOf(model.getDate()));
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(k);

            }
        });
        viewHolder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), PageView.class));
            }
        });
        viewHolder.itemView.setActivated(selectedItems.get(position, false));

    }

    public void removeItem(int position)
    {
        if(position <= items.size())
        {
            PageHandler handler=new PageHandler(context);
            handler.deleteHistory(items.get(position));
            items.remove(position);

        }
            notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView tittle;
        TextView data;
        TextView date;
        ImageView img;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.tittle);
            data = (TextView) itemView.findViewById(R.id.data);
            date = (TextView) itemView.findViewById(R.id.date);
            img = (ImageView) itemView.findViewById(R.id.menu_delete);

        }
    }

}

