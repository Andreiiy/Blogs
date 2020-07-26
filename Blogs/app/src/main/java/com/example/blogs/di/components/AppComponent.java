package com.example.blogs.di.components;

import com.example.blogs.BlogAppi;
import com.example.blogs.MainActivity;
import com.example.blogs.di.modules.AuthorsListAdapterModule;
import com.example.blogs.di.modules.AuthorsPresenterModule;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.di.modules.NetworkModule;
import com.example.blogs.di.modules.ViewAuthorsModule;
import com.example.blogs.presenters.AuthorsPresenter;

import dagger.Component;

@Component(modules = {ContextModule.class, AuthorsPresenterModule.class, AuthorsListAdapterModule.class, ViewAuthorsModule.class, NetworkModule.class})
public interface AppComponent {
    void injectMainActivity(MainActivity mainActivity);
    BlogAppi blogAppi();

}
