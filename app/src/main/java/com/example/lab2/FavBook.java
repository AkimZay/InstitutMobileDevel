package com.example.lab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class FavBook extends android.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager  mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] myString = getResources().getStringArray(R.array.recycler_fav_data);
        List<String> recyclerData = Arrays.asList(myString);

        mAdapter = new FavBookRecyclerViewAdapter(recyclerData);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
