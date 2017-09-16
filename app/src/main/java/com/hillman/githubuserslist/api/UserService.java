package com.hillman.githubuserslist.api;

import com.hillman.githubuserslist.Constants;
import com.hillman.githubuserslist.model.RequsetModel;
import com.hillman.githubuserslist.model.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hllman
 */

public class UserService {

    public <T> T createApi(Class<T> c) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(c);
    }


}
