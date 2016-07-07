package com.esp.retrofitexample.Utils;

import com.jakewharton.retrofit.Ok3Client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;

/**
 * Created by hardikjani on 6/30/16.
 */
public class Utils {

    public static RestAdapter getAdapter() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectTimeout(120, TimeUnit.SECONDS);
        OkHttpClient client = httpBuilder.build();
        return new RestAdapter.Builder().setClient(new Ok3Client(client)).setEndpoint(Configs.Host).build();
    }
}
