package com.example.lab2;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MyAsyncTask extends AsyncTask<String, Integer, String> {
    protected void onPreExecute(){
        Log.i("MyAsyncTask", "PreExecute");
    }
    protected String doInBackground(String... arg) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books.json");
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            Log.i("Error", "Connection null");
            return null;
        }
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                StringBuilder response = new StringBuilder();
                BufferedReader input = new BufferedReader(new InputStreamReader
                        (conn.getInputStream()), 8192);
                String line = null;
                while ((line = input.readLine()) != null)
                {
                    response.append(line);
                }
                input.close();

                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String output) {
        Gson gson = new Gson();
        List<AddBook.Book> books = gson.fromJson(output, new TypeToken<List<AddBook.Book>>(){}.getType());
        for (int i = 0; i < books.size(); i++) {
            AddBook.Book book = books.get(i);
            Log.i("books", book.Author + ", " + book.Genre+", "+ book.Name + ", " + book.PublicationDate);
        }
    }
}
