package com.example.mohammed.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class MovieRecylerAdapter extends RecyclerView.Adapter<MovieRecylerAdapter.MovieViewHolder> {


    private List<Movie> movieList;
    private Context context;

    public MovieRecylerAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        String posterUrl = movieList.get(position).getPoster_path();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.warning).
                        centerCrop().
                        error(R.drawable.warning);

        Glide.with(context).load("http://image.tmdb.org/t/p/w185/" + posterUrl).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.poster.setImageResource(R.drawable.warning);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                }).
                apply(options).into(holder.poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("MOVIE", movieList.get(holder.getAdapterPosition()));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private ProgressBar progressBar;

        public MovieViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.movie_poster);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }
}
