package com.example.lab2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoBook extends android.app.Fragment {
    private TextView tvAuthor;
    private TextView tvGenre;
    private TextView tvName;
    private TextView tvPublicationDate;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_info_book, container, false);

         tvAuthor = view.findViewById(R.id.autor);
         tvGenre = view.findViewById(R.id.genre);
         tvName = view.findViewById(R.id.name);
         tvPublicationDate = view.findViewById(R.id.publish);

         Bundle bundle = getArguments();

         if(bundle != null){
             tvAuthor.setText(bundle.getString("Author"));
             tvGenre.setText(bundle.getString("Genre"));
             tvName.setText(bundle.getString("Name"));
             tvPublicationDate.setText(bundle.getString("PublicationDate"));
         }

        return view;
    }
}
