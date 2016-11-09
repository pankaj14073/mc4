package com.example.pankaj.todo_assignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static listAdapter  adapter;
    ArrayList<list_node> arrayd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("To-Do List");
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3dc8ff")));
       recyclerView = (RecyclerView)findViewById(R.id.alldata);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        PageHandler handler=new PageHandler(getApplicationContext());
         arrayd = handler.getAllHistory();
        adapter = new listAdapter(arrayd,this);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d("TODO", "Add a new task");
                showInputDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void showInputDialog()
    {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.add_item, null);

        final EditText tittle = (EditText) promptView.findViewById(R.id.editTittle);
        final EditText data = (EditText) promptView.findViewById(R.id.editData);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        // setup a dialog wind
        alertDialogBuilder.setCancelable(false)

                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        String t=tittle.getText().toString();
                        String d=data.getText().toString();
                        list_node n=new list_node(t,d);
                        PageHandler handler=new PageHandler(getApplicationContext());
                        handler.addHistory(n);
                        arrayd.add(0,n);
                        adapter.notifyItemInserted(0);
                        recyclerView.smoothScrollToPosition(0);

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();


    }

}
