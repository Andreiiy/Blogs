package com.example.blogs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogs.AuthorActivity;
import com.example.blogs.R;
import com.example.blogs.models.Author;

import java.util.ArrayList;

public class AuthorsListAdapter extends RecyclerView.Adapter<AuthorsListAdapter.AuthorsViewHolder> {

    private ArrayList<Author> authors;
    private Context context;

    public AuthorsListAdapter( Context context) {
          this.context = context;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    @NonNull
    @Override
    public AuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.item_list_authors;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem,parent,false);

        AuthorsListAdapter.AuthorsViewHolder viewHolder = new AuthorsListAdapter.AuthorsViewHolder(view,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsViewHolder holder, int position) {
        holder.bind(authors.get(position));
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public static class AuthorsViewHolder extends RecyclerView.ViewHolder{

        private Author author;
        private TextView tvnameAuthor;

        public AuthorsViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvnameAuthor = (TextView)itemView.findViewById(R.id.tvnameAuthor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AuthorActivity.class);
                    intent.putExtra("author", author); //where user is an instance of User object
                    context.startActivity(intent);
                }
            });
        }

        public void bind(Author author){
            this.author = author;
            tvnameAuthor.setText(author.getName());
        }

    }
}
