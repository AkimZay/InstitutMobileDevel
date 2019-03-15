package com.example.lab2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AddBookRecyclerViewAdapter extends RecyclerView.Adapter<AddBookRecyclerViewAdapter.MyViewHolder> {
    private List<String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.my_text_view);
        }
    }

    public AddBookRecyclerViewAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public AddBookRecyclerViewAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String bookLength = mDataset.get(position);

        holder.mTextView.setText(mDataset.get(position));

        if (bookLength.length() <= 5) {
            holder.mTextView.setTextColor(Color.parseColor("red"));
        } else if (bookLength.length() <= 7) {
            holder.mTextView.setTextColor(Color.parseColor("green"));
        } else if (bookLength.length() > 7)  {
            holder.mTextView.setTextColor(Color.parseColor("blue"));
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
