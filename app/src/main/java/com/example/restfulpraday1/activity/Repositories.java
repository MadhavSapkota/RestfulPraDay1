package com.example.restfulpraday1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.restfulpraday1.R;
import com.example.restfulpraday1.adapter.ReposAdapter;
import com.example.restfulpraday1.model.GitHubRepo;
import com.example.restfulpraday1.rest.APIClient;
import com.example.restfulpraday1.rest.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {
    String receivedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");
        userNameTV = (TextView) findViewById(R.id.userNameTV);
        userNameTV.setText("User: " + receivedUserName);

        mRecyclerView= (RecyclerView) findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);
        loadRepositories();

    }

    public  void loadRepositories(){
        GitHubRepoEndPoint apiServices = (GitHubRepoEndPoint) APIClient.getServiceRepo();
        Call<List<GitHubRepo>> call = apiServices.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.e("Repos", t.toString());

            }
        });

    }
}