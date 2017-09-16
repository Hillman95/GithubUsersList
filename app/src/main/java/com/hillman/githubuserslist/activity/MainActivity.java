package com.hillman.githubuserslist.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.hillman.githubuserslist.R;
import com.hillman.githubuserslist.adapters.UsersAdapter;
import com.hillman.githubuserslist.api.GithubApi;
import com.hillman.githubuserslist.api.UserService;
import com.hillman.githubuserslist.model.RequsetModel;
import com.hillman.githubuserslist.model.User;
import com.hillman.githubuserslist.ui.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    Observable<RequsetModel> callObs;
    RecyclerView recyclerView;
    UserService userService;
    Toolbar toolbar;
    UsersAdapter adapter;
    RelativeLayout searchbox;
    SearchView search;
    List<User> users = new ArrayList<User>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchbox = (RelativeLayout) findViewById(R.id.searchbox);
        search = (SearchView) findViewById(R.id.searchview);
        search.setOnClickListener(view -> {
            search.setIconified(false);
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));


        userService = new UserService();
        final GithubApi api = userService.createApi(GithubApi.class);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    callObs = api.getUsers(newText);
                    callObs.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(requsetModel -> {
                                users.clear();
                                users = requsetModel.getUsers();
                                adapter = new UsersAdapter(users, MainActivity.this);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(adapter);
                            }, throwable -> {});}
                return true;
               }
          });
    }

    @Override
    public void onBackPressed() {
      super.onBackPressed();
    }

}
