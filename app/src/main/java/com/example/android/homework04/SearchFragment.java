package com.example.android.homework04;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment  implements SearchAsync.SearchInterface {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> arrayList;
    String dishName;
    SearchAdapter searchAdapter;
    Fragment fragment;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        arrayList = new ArrayList<>();

        arrayList.add("");
        recyclerView =  getActivity().findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);



        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        fragment = this;
        mAdapter = new SearchAdapter(arrayList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        super.onActivityCreated(savedInstanceState);
        EditText dishNameEditText = getActivity().findViewById(R.id.dishName);
        dishName = dishNameEditText.getText().toString();

        dishNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dishName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        getActivity().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //searchAdapter = new SearchAdapter(this, arrayList);
                SearchAsync searchAsync = new SearchAsync(fragment, arrayList, dishName);

                searchAsync.execute();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void handleRecipeResult(ArrayList<Recipe> recipes) {
       //DisplayRecipeFragment.newInstance(recipes)
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,DisplayRecipeFragment.newInstance(recipes), "DisplayFragment")
                .addToBackStack("SearchFragment").commit();

    }

}
