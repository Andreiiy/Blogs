package com.example.blogs.views;

import com.example.blogs.models.Author;
import com.example.blogs.models.Post;

import java.util.ArrayList;

public interface ViewAuthor {
    void showProgressBar();
    void hideProgressBar();
    void showPosts(ArrayList<Post> listPosts);
    void getAuthorPhoto(String url);
}
