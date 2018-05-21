package com.example.acorona.test;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_BASE_URL = "http://localhost:5432/BusinessServicio/";

    private static Retrofit retrofit;

    private static Gson mGson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(mGson));

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }

    /**
     * or Error Handing when non-OK response is received from Server
     */
    @NonNull
    public static Retrofit retrofit() {
        OkHttpClient client = httpClient.build();
        ;
        return builder.client(client).build();
    }

}