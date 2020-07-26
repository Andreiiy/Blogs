package com.example.blogs.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.blogs.BlogAppi;
import com.example.blogs.di.components.DaggerNetworkComponent;
import com.example.blogs.di.components.NetworkComponent;
import com.example.blogs.di.components.PresentersComponent;
import com.example.blogs.di.modules.ContextModule;
import com.example.blogs.models.Photo;
import com.example.blogs.models.Post;
import com.example.blogs.views.ViewAuthor;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorPresenter {

    private NetworkComponent networkComponent;
    private PresentersComponent presentersComponent;
    private ArrayList<Post> listPosts;
    private ViewAuthor view;

    private BlogAppi networkApi ;
    private Picasso picasso;


    public AuthorPresenter(ViewAuthor view, Context context) {
        this.view = view;
        networkComponent = DaggerNetworkComponent.builder().contextModule(new ContextModule(context)).build();

    }

    public void showListPosts(int userId){
        view.showProgressBar();
        getPosts(userId);
    }

    private void getPosts(int userId){
        networkApi = networkComponent.getNetworkAppi();
        Observable<ArrayList<Post>> authors = networkApi.getPosts(userId);
        authors .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(ArrayList<Post> posts) {
                        view.hideProgressBar();
                        if (posts != null) {
                            listPosts = posts;
                            view.showPosts(listPosts);
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

    public void showAuthorFoto(int userId, final ImageView imageView){
        networkApi = networkComponent.getNetworkAppi();
        picasso = networkComponent.picasso();

        networkApi.getPhoto(userId)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Photo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Photo photo) {
                        if(photo!=null)
                            picasso.get().load(photo.getUrl()).resize(350,250)
                                       .into(imageView, new Callback() {
                                           @Override
                                           public void onSuccess() {
                                               view.hideProgressBar();
                                           }
                                           @Override
                                           public void onError(Exception e) {
                                               Log.d("TAG", "Picasso Error: "+e.getMessage());
                                           }
                                       });
                    }

                    @Override
                    public void onError(Throwable e) { Log.d("TAG", "Error: "+e.getMessage());}

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
