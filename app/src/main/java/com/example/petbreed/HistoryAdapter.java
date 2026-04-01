package com.example.petbreed;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<HistoryItem> list;
    Context context;

    public HistoryAdapter(Context context, List<HistoryItem> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView breed, animal, confidence, date;

        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.imgPet);
            breed = v.findViewById(R.id.tvBreed);
            animal = v.findViewById(R.id.tvAnimal);
            confidence = v.findViewById(R.id.tvConfidence);
            date = v.findViewById(R.id.tvDate);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryItem item = list.get(position);

        if (item.breed.equals("Unknown")) {
            holder.breed.setText("Couldn't Identify");
            holder.breed.setTextColor(Color.RED);
            holder.animal.setText("Type: " + item.animal);
            holder.confidence.setText("Confidence: " + item.confidence + "%");
            holder.date.setText(item.datetime);
        } else {
            holder.breed.setText(item.breed);
            holder.animal.setText("Type: " + item.animal);
            holder.confidence.setText("Confidence: " + item.confidence + "%");
            holder.date.setText(item.datetime);
        }

        Glide.with(context)
                .load(new java.io.File(item.imageUri))
                .into(holder.img);;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
