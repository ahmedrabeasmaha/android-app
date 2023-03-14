package me.ahmedsmaha.project1.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import me.ahmedsmaha.project1.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView author;
    TextView title;
    ImageView image;
    View view;

    ViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
        author = itemView.findViewById(R.id.tvAuthor);
        title = itemView.findViewById(R.id.tvTitle);
        image = itemView.findViewById(R.id.newImage);
        view = itemView;
    }
}