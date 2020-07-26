package com.example.blogs.di.modules;

import android.content.Context;

import com.example.blogs.adapters.AuthorsListAdapter;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AuthorsListAdapterModule {

    @Provides
    public AuthorsListAdapter authorsListAdapter(Context context){
        return new AuthorsListAdapter(context);
    }
}
