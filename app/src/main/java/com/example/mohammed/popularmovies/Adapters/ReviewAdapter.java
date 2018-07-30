package com.example.mohammed.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.popularmovies.R;
import com.example.mohammed.popularmovies.jsonModels.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewVH> {

    private Context context;
    private List<Review> reviews;


    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.review_layout_item, parent, false);

        return new ReviewVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewVH holder, int position) {


        holder.reviewAuther.setText(reviews.get(holder.getAdapterPosition()).getAuthor());
        holder.review.setText(reviews.get(holder.getAdapterPosition()).getContent());


        if (reviews.get(holder.getAdapterPosition()).getContent().length() >= 60 * 5) {
            Toast.makeText(context, "So Much", Toast.LENGTH_SHORT).show();
            holder.readMore.setVisibility(View.VISIBLE);
            holder.readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(reviews.get(holder.getAdapterPosition()).getUrl()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewVH extends RecyclerView.ViewHolder {

        private TextView readMore;
        private TextView review;
        private TextView reviewAuther;

        ReviewVH(View itemView) {
            super(itemView);
            review = itemView.findViewById(R.id.review_text);
            readMore = itemView.findViewById(R.id.read_more);
            reviewAuther = itemView.findViewById(R.id.review_auther);
        }
    }
}
