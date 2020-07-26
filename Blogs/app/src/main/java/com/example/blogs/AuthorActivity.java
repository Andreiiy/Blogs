package com.example.blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blogs.adapters.PostsListAdapter;
import com.example.blogs.di.components.DaggerPresentersComponent;
import com.example.blogs.di.components.PresentersComponent;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.di.modules.ViewAuthorModule;
import com.example.blogs.models.Author;
import com.example.blogs.models.Post;
import com.example.blogs.presenters.AuthorPresenter;
import com.example.blogs.presenters.AuthorsPresenter;
import com.example.blogs.views.ViewAuthor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity implements ViewAuthor {

    private ProgressDialog progressDialog;
    private RecyclerView rvListPosts;
    private AuthorPresenter presenter;
    private ArrayList<Post> listPosts;
    private ImageView ivAuthor;
    private TextView tvName;
    private TextView tvAmountPosts;
    private Author author;
    private PresentersComponent presentersComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        presentersComponent = DaggerPresentersComponent
                .builder()
                .contextModule(new ContextModule(this))
                .viewAuthorModule(new ViewAuthorModule(this))
                .build();
        initViews();

        if(getIntent().getExtras() != null) {
            author = (Author) getIntent().getSerializableExtra("author");
            tvName.setText(author.getName());
            presenter = presentersComponent.authorPresenter();
            presenter.showListPosts(author.getId());
            presenter.showAuthorFoto(author.getId(),ivAuthor);

//            String imageUri = "https://ocdn.eu/pulscms-transforms/1/fEbk9lLaHR0cDovL29jZG4uZXUvaW1hZ2VzL3B1bHNjbXMvWVdFN01EQV8vY2NiODYwYzU5Zjc3ZDMxMzc4N2YzNzE5MzhjOTk3NjIuanBnkZMCzQSwAIEAAQ";
////            Picasso.get().load(imageUri).resize(350,250)
////                                        .into(ivAuthor);

        }
    }

    @Override
    public void showProgressBar() {

        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.hide();
    }

    @Override
    public void showPosts(ArrayList<Post> listPosts) {
        this.listPosts = listPosts;
        //create new adapter
        PostsListAdapter postsListAdapter = new PostsListAdapter(this.listPosts);

        tvAmountPosts.setText(listPosts.size()+" posts");

        //put adapter to recyclerview
        rvListPosts.setAdapter(postsListAdapter);
    }

    @Override
    public void getAuthorPhoto(String url) {

    }


    private void initViews(){
        ivAuthor = (ImageView)findViewById(R.id.ivAuthor);
        tvName = (TextView) findViewById(R.id.tvName);
        tvAmountPosts = (TextView) findViewById(R.id.tvAmountPosts);

//ReciclerView
        rvListPosts = (RecyclerView)findViewById(R.id.rvListPosts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        //put gridManager to recyclerview
        rvListPosts.setLayoutManager(gridLayoutManager);
        rvListPosts.setHasFixedSize(true);
//ProgressDialog
        progressDialog = new ProgressDialog(AuthorActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
    }
}
