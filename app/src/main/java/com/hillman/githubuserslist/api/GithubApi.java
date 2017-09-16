package com.hillman.githubuserslist.api;

import com.hillman.githubuserslist.model.RequsetModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hllman
 */

public interface GithubApi {
    @GET ("/search/users")
    Observable<RequsetModel> getUsers(@Query("q") String query);
}
