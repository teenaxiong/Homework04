package com.example.android.homework04;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

public class DisplayRecycleAdapter extends RecyclerView.Adapter <DisplayRecycleAdapter.ViewHolder> {
    ArrayList<Recipe>arrayList;
Context displayRecipeFragment;

    public DisplayRecycleAdapter(Context displayRecipeFragment, ArrayList<Recipe> arrayList) {
        this.displayRecipeFragment = displayRecipeFragment;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.display_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.textViewTitle.setText(arrayList.get(i).getTitle());
        viewHolder.textViewIngrediants.setText(arrayList.get(i).getIngredients());
        if(!viewHolder.imageView.equals("")){
            Picasso.get().load(arrayList.get(i).getThumbnail()).into(viewHolder.imageView);
        }

        viewHolder.textViewURL.setText(arrayList.get(i).getHref());


        viewHolder.textViewURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = viewHolder.textViewURL.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                displayRecipeFragment.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewURL;
        TextView textViewIngrediants;
        ImageView imageView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
              textViewTitle = itemView.findViewById(R.id.textViewTitle);
             textViewURL = itemView.findViewById(R.id.textViewURL);
             textViewIngrediants = itemView.findViewById(R.id.textViewIngrediants);
             imageView = itemView.findViewById(R.id.imageView);


        }
    }

}
