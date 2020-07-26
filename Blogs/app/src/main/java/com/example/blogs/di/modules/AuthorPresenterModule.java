package com.example.blogs.di.modules;

import android.content.Context;

import com.example.blogs.presenters.AuthorPresenter;
import com.example.blogs.views.ViewAuthor;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class,ViewAuthorModule.class})
public class AuthorPresenterModule {

    @Provides
    public AuthorPresenter authorPresenter(ViewAuthor viewAuthor, Context context){
        return new AuthorPresenter(viewAuthor,context);
    }


}
