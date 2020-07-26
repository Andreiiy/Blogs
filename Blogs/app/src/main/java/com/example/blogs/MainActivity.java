package com.example.blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.blogs.adapters.AuthorsListAdapter;
import com.example.blogs.di.components.AppComponent;
import com.example.blogs.di.components.DaggerAppComponent;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.di.modules.ViewAuthorsModule;
import com.example.blogs.models.Author;
import com.example.blogs.presenters.AuthorsPresenter;
import com.example.blogs.views.ViewAuthors;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ViewAuthors {

    private ProgressDialog progressDialog;
    private RecyclerView rvListAuthors;
    AppComponent appComponent;
    ArrayList<Author> listAuthors;
    @Inject
    AuthorsPresenter presenter;
    @Inject
    AuthorsListAdapter authorsListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .viewAuthorsModule(new ViewAuthorsModule(this))
                .build();
        appComponent.injectMainActivity(this);


        initRV();


        presenter.showListAuthors();
    }

    @Override
    public void showProgressBar() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showAuthors(ArrayList<Author> listAuthors) {

        this.listAuthors = listAuthors;
        //create new adapter
//        AuthorsListAdapter authorsListAdapter = new AuthorsListAdapter(this);
        authorsListAdapter.setAuthors(this.listAuthors);
        //put adapter to recyclerview
        rvListAuthors.setAdapter(authorsListAdapter);
    }

    private void initRV(){

        rvListAuthors = (RecyclerView)findViewById(R.id.rvListAuthors);
        rvListAuthors.setLayoutManager(new LinearLayoutManager(this));
        rvListAuthors.setHasFixedSize(true);


    }


}
