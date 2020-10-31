package com.ucucite.registerlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final  String DBNAME = "Login.db";

    public DBHelper(Context context){
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"users\" (\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"completename\"\tTEXT,\n" +
                "\t\"address\"\tTEXT,\n" +
                "\t\"status\"\tTEXT,\n" +
                "\t\"gender\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"username\")\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");


        }
    public boolean insertData(String username, String password, String completename, String address, String status, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password", password);
        contentValues.put("completename", completename);
        contentValues.put("address", address);
        contentValues.put("status", status);
        contentValues.put("gender", gender);

        long result = db.insert("users", null, contentValues);

        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from users where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
