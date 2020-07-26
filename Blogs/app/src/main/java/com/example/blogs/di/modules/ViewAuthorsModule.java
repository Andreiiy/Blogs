package com.example.blogs.di.modules;

import com.example.blogs.views.ViewAuthor;
import com.example.blogs.views.ViewAuthors;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewAuthorsModule {

    ViewAuthors viewAuthors;

    public ViewAuthorsModule(ViewAuthors viewAuthors) {
        this.viewAuthors = viewAuthors;
    }

    @Provides
    public ViewAuthors provideViewAuthor(){
        return this.viewAuthors;
    }
}
