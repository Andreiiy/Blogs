package com.example.blogs.views;

import com.example.blogs.models.Author;

import java.util.ArrayList;

public interface ViewAuthors {

    void showProgressBar();
    void hideProgressBar();
    void showAuthors(ArrayList<Author> listAuthors);
}
