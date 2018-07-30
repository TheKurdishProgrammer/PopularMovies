package com.example.mohammed.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohammed.popularmovies.NetWorkUtils.MovieLinkConstants;
import com.example.mohammed.popularmovies.R;
import com.example.mohammed.popularmovies.jsonModels.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerVH> {

    private Context context;
    private List<Trailer> trailers;


    public TrailerAdapter(Context context, List<Trailer> trailers) {
        this.context = context;
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.trailer_layout, parent, false);

        return new TrailerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrailerVH holder, int position) {

        Log.e("Pos", String.valueOf(position));
        holder.trailer.setText(context.getString(R.string.trailer_label, (position + 1)));

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        MovieLinkConstants.youtubeBaseURl + trailers.get(holder.getAdapterPosition()).
                                getKey()));

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerVH extends RecyclerView.ViewHolder {

        private TextView trailer;
        private AppCompatImageButton play;

        TrailerVH(View itemView) {
            super(itemView);
            trailer = itemView.findViewById(R.id.trailer_item);

            play = itemView.findViewById(R.id.play_button);
        }
    }
}
