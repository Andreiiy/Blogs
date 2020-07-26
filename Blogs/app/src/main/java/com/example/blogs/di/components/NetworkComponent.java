package com.example.blogs.di.components;

import com.example.blogs.BlogAppi;
import com.example.blogs.MainActivity;
import com.example.blogs.di.modules.AuthorsListAdapterModule;
import com.example.blogs.di.modules.AuthorsPresenterModule;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.di.modules.NetworkModule;
import com.example.blogs.di.modules.ViewAuthorsModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@Component(modules = { NetworkModule.class,ContextModule.class})
public interface NetworkComponent {
           BlogAppi getNetworkAppi();
           Picasso picasso();

}
