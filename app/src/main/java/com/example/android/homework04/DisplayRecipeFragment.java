package com.example.android.homework04;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayRecipeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    static ArrayList<Recipe> arrayList;

    public DisplayRecipeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static String param1;
    private static String param2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddExpense.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayRecipeFragment newInstance(ArrayList<Recipe>a) {
        DisplayRecipeFragment fragment = new DisplayRecipeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, a);
      //  args.putStringArrayList(a);
        fragment.setArguments(args);

        arrayList = args.getParcelableArrayList(ARG_PARAM1);

        for (Recipe x : arrayList) {
            Log.d("mJJJmmm " , " ddd" + x.getTitle() );
        }
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView =  getActivity().findViewById(R.id.displayRecycleView);

       // recyclerView.setHasFixedSize(true);


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DisplayRecycleAdapter(arrayList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_display_recipe, container, false);
    }

}
