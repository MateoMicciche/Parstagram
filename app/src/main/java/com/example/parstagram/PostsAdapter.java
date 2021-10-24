package com.example.parstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvPostDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPostDate = itemView.findViewById(R.id.tvPostDate);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            String postTime = getFormattedTimeStamp(post.getCreatedAt());
            tvPostDate.setText(postTime);
        }

    }

    private String getFormattedTimeStamp(Date createdAt) {
        Date currentDate = new Date();
        float seconds = (float) Math.floor((currentDate.getTime() - createdAt.getTime()) / 1000);

        float interval = (seconds / 31536000);

        if (interval > 1) {
            return (int) Math.floor(interval) + " year(s) ago";
        }
        interval = seconds / 2592000;
        if (interval > 1) {
            return (int)Math.floor(interval) + " month(s) ago";
        }
        interval = seconds / 86400;
        if (interval > 1) {
            return (int)Math.floor(interval) + " day(s) ago";
        }
        interval = seconds / 3600;
        if (interval > 1) {
            return (int)Math.floor(interval) + " hour(s) ago";
        }
        interval = seconds / 60;
        if (interval > 1) {
            return (int)Math.floor(interval) + " minute(s) ago";
        }
        return (int)Math.floor(seconds) + " second(s) ago";
    }

}
