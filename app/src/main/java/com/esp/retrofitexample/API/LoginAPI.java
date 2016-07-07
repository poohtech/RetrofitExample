package com.esp.retrofitexample.API;

import android.content.Context;

import com.esp.retrofitexample.Bean.GetRoutBean;
import com.esp.retrofitexample.Bean.LoginBean;
import com.esp.retrofitexample.Utils.Configs;
import com.esp.retrofitexample.Utils.ResponseListener;
import com.esp.retrofitexample.Utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by hardikjani on 6/29/16.
 */
public class LoginAPI {

    public static final String appendURL = "/user/login";

    public LoginAPI(Context context, final ResponseListener responseListener, String username, String password) {


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("device_type", "2");
        params.put("udid", "");
        params.put("push_id", "");

        System.out.println("--------params-------" + params);

        GetLoginAPI apiMethod = Utils.getAdapter().create(GetLoginAPI.class);
        apiMethod.getLoginBean(params, new Callback<LoginBean>() {
            @Override
            public void success(LoginBean getLoginBean, Response response) {

                if (response.getStatus() == 200) {
                    responseListener.onResponce(Configs.TAG_LOGIN, Configs.RESULT_OK, getLoginBean);
                } else {
                    responseListener.onResponce(Configs.TAG_LOGIN, Configs.RESULT_FAIL, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("--------RetrofitError-------" + error);
                responseListener.onResponce(Configs.TAG_LOGIN, Configs.RESULT_FAIL, error.getUrl());
            }
        });
    }

    public interface GetLoginAPI {
        @Headers("AUTH-KEY: PROJECT@#2016")
        @POST(appendURL)
        void getLoginBean(@Body HashMap<String, String> arguments, Callback<LoginBean> callback);
    }

}