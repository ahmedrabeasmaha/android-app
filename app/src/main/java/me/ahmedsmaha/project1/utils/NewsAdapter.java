package me.ahmedsmaha.project1.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.ahmedsmaha.project1.Model.RetroNews;
import me.ahmedsmaha.project1.R;

public class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {

    RetroNews retroNews;

    Context context;

    public NewsAdapter(RetroNews retroNews, Context context) {
        this.retroNews = retroNews;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(retroNews.getArticles().get(position).getSource().getName());
        holder.author.setText(retroNews.getArticles().get(position).getAuthor());
        holder.title.setText(retroNews.getArticles().get(position).getTitle());
        Picasso.get().load(retroNews.getArticles().get(position).getUrlToImage()).error(R.drawable.tesla_img).into(holder.image);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.news_cart, parent, false);
        return new ViewHolder(photoView);
    }

    @Override
    public int getItemCount() {
        return this.retroNews.getArticles().size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
