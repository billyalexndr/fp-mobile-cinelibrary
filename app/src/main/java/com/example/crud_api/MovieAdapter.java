package com.example.crud_api;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<MovieItem> result;
    private Context context;
    String bookmark;

    public MovieAdapter(List<MovieItem> result, Context context) {
        this.result = result;
        this.context = context;
    }

    public MovieAdapter(Context context, String bookmark) {
        this.context = context;
        this.bookmark = bookmark;
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
    public void setData(List<MovieItem> newData) {
        result = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return result != null ? result.size() : 0;
    }

    // Define an interface for the click listener
    public interface OnBookmarkClickListener {
        void onBookmarkClick(MovieItem movie);
    }

    private OnBookmarkClickListener bookmarkClickListener;
    // Method to set the click listener
    public void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        this.bookmarkClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_released, tv_runtime, tv_genre, tv_country, tv_imdbrating;
        ImageView imageView;
        Button btnBookmark;
        AppDatabase appDB = new AppDatabase(context);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_released = itemView.findViewById(R.id.tv_released);
            tv_runtime = itemView.findViewById(R.id.tv_runtime);
            tv_genre = itemView.findViewById(R.id.tv_genre);
            tv_country = itemView.findViewById(R.id.tv_country);
            tv_imdbrating = itemView.findViewById(R.id.tv_imdbrating);
            imageView = itemView.findViewById(R.id.imageView);
            btnBookmark = itemView.findViewById(R.id.btn_bookmark);
        }

        public void bind(MovieItem movieItem) {
            tv_title.setText(movieItem.Title);
            tv_released.setText(movieItem.Released);
            tv_runtime.setText(movieItem.Runtime);
            tv_genre.setText(movieItem.Genre);
            tv_country.setText(movieItem.Country);
            tv_imdbrating.setText(movieItem.imdbRating);
            Glide.with(context).load(movieItem.getImages()).into(imageView);
            btnBookmark.setText(bookmark);
            btnBookmark.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && bookmarkClickListener != null) {
                    MovieItem movie = result.get(position);
//                    bookmarkClickListener.onBookmarkClick(movie);
                    if (bookmark == "Bookmark") {
                        appDB.addMovieToLibrary(movieItem, context);
                    } else {
//                        add delete function
                    }

                }
            });
        }

        private void addToLibrary(MovieItem movie) {
            // Code to add the movie to the library (SQLite database)
            AppDatabase database = new AppDatabase(context);

            // Assuming you have a method to insert data into the database
            database.addMovieToLibrary(movie, context);

            // Notify user or perform any additional actions upon adding to the library
            Toast.makeText(context, "Movie added to Library", Toast.LENGTH_SHORT).show();
        }
    }
}
