package com.example.blogs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogs.R;
import com.example.blogs.models.Author;
import com.example.blogs.models.Post;

import java.util.ArrayList;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsViewHolder> {

    private ArrayList<Post> posts;

    public PostsListAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsListAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.item_list_posts;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem,parent,false);

        PostsListAdapter.PostsViewHolder viewHolder = new PostsListAdapter.PostsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsListAdapter.PostsViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder{

        private Post post;
        private TextView tvTitle;
        private TextView tvBody;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView)itemView.findViewById(R.id.tvBody);
        }

        public void bind(Post post){
            this.post = post;
            tvTitle.setText(post.getTitle());
            tvBody.setText(post.getBody());
        }

    }
}
