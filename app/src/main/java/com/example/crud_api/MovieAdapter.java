package com.example.crud_api;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<MovieItem> result;
    Activity activity;

    private OnBookmarkClickListener onBookmarkClickListener;

    // Interface untuk menangani klik tombol bookmark
    public interface OnBookmarkClickListener {
        void onBookmarkClick(int position);
    }

    // Method untuk mengatur listener klik bookmark
    public void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        this.onBookmarkClickListener = listener;
    }

    public MovieAdapter(List<MovieItem> result, Activity activity) {
        this.result = result;
        this.activity = activity;
    }

    public MovieAdapter(MyMovie myMovie) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int
            title) {
        holder.bind(result.get(title));
    }

    @Override
    public int getItemCount() {
        return result != null ? result.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_released, tv_runtime, tv_genre, tv_country, tv_imdbrating;
        ImageView imageView;

        // Bookmark Button
        Button btnBookmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_released = itemView.findViewById(R.id.tv_released);
            tv_runtime = itemView.findViewById(R.id.tv_runtime);
            tv_genre = itemView.findViewById(R.id.tv_genre);
            tv_country = itemView.findViewById(R.id.tv_country);
            tv_imdbrating = itemView.findViewById(R.id.tv_imdbrating);
            imageView = itemView.findViewById(R.id.imageView);

            // Temukan tombol bookmark
            btnBookmark = itemView.findViewById(R.id.btnBookmark);

            // Set click listener untuk tombol bookmark
            btnBookmark.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && onBookmarkClickListener != null) {
                    onBookmarkClickListener.onBookmarkClick(position);
                }
            });
        }

        // Metode untuk mengatur tampilan bookmark pada ViewHolder
        public void setBookmark(boolean isBookmarked) {
            if (isBookmarked) {
                btnBookmark.setText("Bookmarked");
                // Ubah warna latar belakang atau tampilan lainnya untuk menandakan sudah di-bookmark
                btnBookmark.setBackgroundResource(R.drawable.bookmark_button_bg); // Ubah sesuai kebutuhan
            } else {
                btnBookmark.setText("Bookmark");
                // Ubah warna latar belakang atau tampilan lainnya untuk menandakan belum di-bookmark
                btnBookmark.setBackgroundResource(R.drawable.unbookmark_button_bg); // Ubah sesuai kebutuhan
            }
        }

        public void bind(MovieItem movieItem) {
            tv_title.setText(movieItem.Title);
            tv_released.setText(movieItem.Released);
            tv_runtime.setText(movieItem.Runtime);
            tv_genre.setText(movieItem.Genre);
            tv_country.setText(movieItem.Country);
            tv_imdbrating.setText(movieItem.imdbRating);
            Glide.with(activity).load(movieItem.getImages()).into(imageView);
        }
    }
}
