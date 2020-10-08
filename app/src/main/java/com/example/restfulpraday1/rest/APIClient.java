package com.example.restfulpraday1.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;

    private static Retrofit.Builder getRetrofitInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);
    }


    public static GitHubUserEndPoints getService() {
        return getRetrofitInstance().build().create(GitHubUserEndPoints.class);
    }




//
//    public static Retrofit getClient(){
//        if (getClient() == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return  retrofit;
//    }

}