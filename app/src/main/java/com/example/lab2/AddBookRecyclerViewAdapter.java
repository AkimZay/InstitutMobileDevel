package com.example.lab2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class AddBookRecyclerViewAdapter extends RecyclerView.Adapter<AddBookRecyclerViewAdapter.MyViewHolder> {
    private List<Book> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.my_text_view);
        }
    }

    public AddBookRecyclerViewAdapter(List<Book> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public AddBookRecyclerViewAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).getName());

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoBook infoBook = new InfoBook();
                Bundle bundle = new Bundle();
                bundle.putString("Author", mDataset.get(position).getAuthor());
                bundle.putString("Genre", mDataset.get(position).getGenre());
                bundle.putString("Name", mDataset.get(position).getName());
                bundle.putString("PublicationDate", mDataset.get(position).getPublicationDate().toString());
                infoBook.setArguments(bundle);

                final Context context = v.getContext();

                FragmentManager fm = ((Activity) context).getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.content_frame, infoBook)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
