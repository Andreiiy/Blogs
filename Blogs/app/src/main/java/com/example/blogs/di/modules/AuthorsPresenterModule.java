package com.example.blogs.di.modules;

import android.content.Context;

import com.example.blogs.presenters.AuthorsPresenter;
import com.example.blogs.views.ViewAuthor;
import com.example.blogs.views.ViewAuthors;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewAuthorsModule.class,ContextModule.class})
public class AuthorsPresenterModule {

    @Provides
   public AuthorsPresenter authorsPresenter(ViewAuthors viewAuthors,Context context){
        return new AuthorsPresenter(viewAuthors,context);
    }



}
