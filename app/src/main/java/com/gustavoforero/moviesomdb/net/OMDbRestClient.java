package com.gustavoforero.moviesomdb.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum OMDbRestClient {

    INSTANCE;

    private OMDbApi mOMDBOMDbApi;

    OMDbRestClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofitService = new Retrofit.Builder().client(client)
                .baseUrl(OMDbApi.URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        mOMDBOMDbApi = retrofitService.create(OMDbApi.class);
    }

    public OMDbApi getApi() {
        return mOMDBOMDbApi;
    }

}
