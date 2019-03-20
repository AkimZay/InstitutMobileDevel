package com.example.lab2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class FavBookRecyclerViewAdapter extends RecyclerView.Adapter<AddBookRecyclerViewAdapter.MyViewHolder> {
    private List<String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAuthor;
        public TextView tvName;
        public TextView tvDate;

        public LinearLayout bookLayout;

        public MyViewHolder(View v) {
            super(v);

            bookLayout = v.findViewById(R.id.bookLayout);

            tvAuthor = v.findViewById(R.id.tvAuthor);
            tvName = v.findViewById(R.id.tvName);
            tvDate = v.findViewById(R.id.tvPublishDate);
        }
    }

    public FavBookRecyclerViewAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public AddBookRecyclerViewAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        AddBookRecyclerViewAdapter.MyViewHolder vh = new AddBookRecyclerViewAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AddBookRecyclerViewAdapter.MyViewHolder holder, int position) {
        String bookLength = mDataset.get(position);

        holder.tvName.setText(mDataset.get(position));

        if (bookLength.length() <= 5) {
            holder.tvName.setTextColor(Color.parseColor("red"));
        } else if (bookLength.length() <= 7) {
            holder.tvName.setTextColor(Color.parseColor("green"));
        } else if (bookLength.length() > 7)  {
            holder.tvName.setTextColor(Color.parseColor("blue"));
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
