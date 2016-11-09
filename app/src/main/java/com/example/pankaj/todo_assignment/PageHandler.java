package com.example.pankaj.todo_assignment;

/**
 * Created by pankaj on 9/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by pankaj on 18/10/16.
 */
public class PageHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Todo";
    private static final String TABLE_TODO = "TodoTable";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITTLE = "tittle";
    private static final String KEY_DATA = "data";
    private static final String KEY_DATE = "date";



    public PageHandler(Context context)
    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +KEY_TITTLE+" TEXT,"
                + KEY_DATA + " TEXT,"
                +KEY_DATE + " TEXT"
                + ")";

        Log.v("CHECK :  ",CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }
    public void addHistory(list_node node)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITTLE, node.getTitle());
        values.put(KEY_DATA, String.valueOf(node.getData()));
        values.put(KEY_DATE, node.getDate());
        db.insert(TABLE_TODO, null, values);
        db.close();

    }

    public list_node getPerson(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TODO, new String[] { KEY_ID,
                        KEY_TITTLE, KEY_DATA,KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null,null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        list_node node = new list_node(cursor.getString(1),cursor.getString(2));

        return node;
    }

    public ArrayList< list_node> getAllHistory()
    {
        ArrayList< list_node> HistoryList = new ArrayList< list_node>();
        String selectQuery = "SELECT * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                list_node node = new list_node(cursor.getString(1), cursor.getString(2));
                HistoryList.add(node);
            } while (cursor.moveToNext());
        }
        return HistoryList;
    }


    public void deleteHistory(list_node node)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_TITTLE + " = ?",
                new String[] { String.valueOf(node.getTitle()) });
        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TODO);

    }
}

