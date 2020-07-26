package com.example.blogs.di.components;


import com.example.blogs.di.modules.AuthorPresenterModule;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.di.modules.ViewAuthorModule;
import com.example.blogs.presenters.AuthorPresenter;
import com.example.blogs.presenters.AuthorsPresenter;
import com.example.blogs.views.ViewAuthor;

import dagger.Component;

@Component(modules = {ContextModule.class, AuthorPresenterModule.class, ViewAuthorModule.class})
public interface PresentersComponent {

    AuthorPresenter authorPresenter();
}
