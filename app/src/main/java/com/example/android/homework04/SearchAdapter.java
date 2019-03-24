package com.example.android.homework04;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.ViewHolder> {

    ArrayList<String>arrayList;
    SearchAsync.SearchInterface searchInterface;

    public SearchAdapter( ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        int x = viewHolder.getLayoutPosition();

        if(arrayList.get(x).length() > 0) {
            viewHolder.item.setText(arrayList.get(x));
        }
        else{
            viewHolder.item.setText(null);
        }

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class ViewHolder extends RecyclerView.ViewHolder {
        EditText item;
        FloatingActionButton floatingActionButton;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            floatingActionButton = itemView.findViewById(R.id.floatingActionButton);

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(arrayList.size()!=5) {
                        if (position == arrayList.size() - 1) {
                            arrayList.add(position + 1, "");
                            notifyItemInserted(position + 1);
                            FloatingActionButton a = view.findViewById(R.id.floatingActionButton);
                            a.setImageResource(R.drawable.delete);
                            item.setEnabled(false);
                        } else {
                            arrayList.remove(position);
                            notifyItemRemoved(position);
                        }
                    }
                }
            });
            item.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    arrayList.set(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });



        }
    }

}
