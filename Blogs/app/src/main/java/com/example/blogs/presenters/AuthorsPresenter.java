package com.example.blogs.presenters;

import android.content.Context;
import android.util.Log;

import com.example.blogs.BlogAppi;
import com.example.blogs.di.components.DaggerNetworkComponent;
import com.example.blogs.di.components.NetworkComponent;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.models.Author;
import com.example.blogs.views.ViewAuthors;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.blogs.di.components.DaggerAppComponent;

public class AuthorsPresenter {

    private NetworkComponent networkComponent;
    private ArrayList<Author> listAuthors;
    private ViewAuthors view;

    private BlogAppi networkApi ;



    public AuthorsPresenter(ViewAuthors view, Context context) {
           this.view = view;
       networkComponent = DaggerNetworkComponent.builder()
                .contextModule(new ContextModule(context))
                .build();
        networkApi = networkComponent.getNetworkAppi();
    }


    public void showListAuthors(){
        view.showProgressBar();
        getAuthors();
    }

    private void getAuthors(){
        Observable<ArrayList<Author>> authors = networkApi.getAuthors();
        authors .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Author>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ArrayList<Author> authors) {
                        view.hideProgressBar();
                        if (authors != null) {
                            listAuthors = authors;
                            view.showAuthors(listAuthors);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Teg","Error :"+e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {}
                });


    }

}
