package com.example.lab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class AddBook extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyAsyncTask ask = new MyAsyncTask();
        ask.execute();

        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        mySwipeRefreshLayout = view.findViewById(R.id.mySwipeRefreshLayout);

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               mySwipeRefreshLayout.setRefreshing(false);
            }

        });

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] myString = getResources().getStringArray(R.array.recycler_data);
        List<String> recyclerData = Arrays.asList(myString);

        mAdapter = new AddBookRecyclerViewAdapter(recyclerData);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

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
        Integer PublicationDate ;
    }
}
