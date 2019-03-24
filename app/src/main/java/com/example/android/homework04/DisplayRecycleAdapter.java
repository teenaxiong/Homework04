package com.example.android.homework04;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayRecycleAdapter extends RecyclerView.Adapter <DisplayRecycleAdapter.ViewHolder> {
    ArrayList<Recipe>arrayList;

    public DisplayRecycleAdapter(ArrayList<Recipe> arrayList) {
        this.arrayList = arrayList;
        for (Recipe x : arrayList) {
            Log.d("mmmm " , " ddd" + x.getTitle() );
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.display_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        for (Recipe x : arrayList) {
            Log.d("iiii " , " ddd" + x.getTitle() );
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textViewTitle.setText(arrayList.get(i).getTitle());
        viewHolder.textViewIngrediants.setText(arrayList.get(i).getIngredients());
        viewHolder.textViewURL.setText(arrayList.get(i).getHref());
        for (Recipe x : arrayList) {
            Log.d("fffkkkkkkmbbf " , " ddd" + x.getTitle() );
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewURL;
        TextView textViewIngrediants;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
          textViewTitle = itemView.findViewById(R.id.textViewTitle);
             textViewURL = itemView.findViewById(R.id.textViewURL);
             textViewIngrediants = itemView.findViewById(R.id.textViewIngrediants);
            for (Recipe x : arrayList) {
                Log.d("dddd " , " ddd" + x.getTitle() );
            }

        }
    }
}
