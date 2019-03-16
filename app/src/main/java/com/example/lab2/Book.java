package com.example.lab2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("Author")
    @Expose
    String Author ;
    @SerializedName("Genre")
    @Expose
    String Genre ;
    @SerializedName("Name")
    @Expose
    String Name;
    @SerializedName("PublicationDate")
    @Expose
    String PublicationDate ;

    public String getAuthor(){
        return this.Author;
    }
    public String getGenre(){
        return this.Genre;
    }
    public String getName(){
        return this.Name;
    }
    public String getPublicationDate(){
        return this.PublicationDate;
    }
}
