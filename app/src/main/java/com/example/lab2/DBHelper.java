package com.example.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DBHelper extends SQLiteOpenHelper {

    private final Context fContext;
    private static final String DATABASE = "MyDatabase";
    public static final String TABLE_USER = "User";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        fContext = context;
    }
    public DBHelper(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version,
                    DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        fContext = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBHelper(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
        fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User ("
                + "id integer primary key autoincrement,"
                + "login text,"
                + "password text" + ");");
        db.execSQL("create table Book ("
                + "id integer primary key autoincrement,"
                + "author text,"
                + "genre text,"
                + "publishDate text,"
                + "name text" + ");");

        ContentValues values = new ContentValues();

        Resources res = fContext.getResources();

        XmlResourceParser _xml = res.getXml(R.xml.users_record);
        try {
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Ищем теги record
                if ((eventType == XmlPullParser.START_TAG)
                        && (_xml.getName().equals("record"))) {
                    String login = _xml.getAttributeValue(0);
                    String password = _xml.getAttributeValue(1);
                    values.put("login", login);
                    values.put("password", password);
                    db.insert(TABLE_USER, null, values);
                }
                eventType = _xml.next();
            }
        }
        catch (XmlPullParserException e) {
            Log.e("Test", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("Test", e.getMessage(), e);

        } finally {
            _xml.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(db);
    }
}
