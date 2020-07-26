package com.example.blogs.di.modules;

import com.example.blogs.views.ViewAuthor;

import dagger.Module;
import dagger.Provides;

@Module
    public class ViewAuthorModule {

        ViewAuthor viewAuthor;

        public ViewAuthorModule(ViewAuthor viewAuthor) {
            this.viewAuthor = viewAuthor;
        }

        @Provides
        public ViewAuthor provideViewAuthor(){
            return this.viewAuthor;
        }
    }

