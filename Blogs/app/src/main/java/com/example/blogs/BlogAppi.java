package com.example.blogs;

import com.example.blogs.models.Author;
import com.example.blogs.models.Photo;
import com.example.blogs.models.Post;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BlogAppi {

    @GET("/users")
    Observable<ArrayList<Author>> getAuthors();

    @GET("/users/{id}")
    Flowable<Author> getAuthor(
            @Path("id") int id
    );

    @GET("/posts/")
    //@GET("/posts/?userId={id}")
    Observable<ArrayList<Post>> getPosts(@Query(value = "userId") int id);

    @GET("/photos/{id}")
    Flowable<Photo> getPhoto( @Path("id") int id);

    //Flowable<Author>getAuthors();
}
