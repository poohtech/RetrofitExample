package com.esp.retrofitexample.API;

import android.content.Context;

import com.esp.retrofitexample.Bean.LoginBean;
import com.esp.retrofitexample.Bean.RegistrationBean;
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
public class RegistrationAPI {

    public static final String appendURL = "/user/register";

    public RegistrationAPI(Context context, final ResponseListener responseListener, String username, String email, String password) {


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("role_id", "2");
        params.put("first_name", "");
        params.put("last_name", "");
        params.put("device_type", "2");
        params.put("udid", "");
        params.put("push_id", "");

        System.out.println("--------params-------" + params);

        GetLoginAPI apiMethod = Utils.getAdapter().create(GetLoginAPI.class);
        apiMethod.getRegisterBean(params, new Callback<RegistrationBean>() {
            @Override
            public void success(RegistrationBean getRegistrationBean, Response response) {

                if (response.getStatus() == 200) {
                    responseListener.onResponce(Configs.TAG_REGISTER, Configs.RESULT_OK, getRegistrationBean);
                } else {
                    responseListener.onResponce(Configs.TAG_REGISTER, Configs.RESULT_FAIL, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("--------RetrofitError-------" + error);
                responseListener.onResponce(Configs.TAG_REGISTER, Configs.RESULT_FAIL, error.getUrl());
            }
        });
    }

    public interface GetLoginAPI {
        @Headers("AUTH-KEY: PROJECT@#2016")
        @POST(appendURL)
        void getRegisterBean(@Body HashMap<String, String> arguments, Callback<RegistrationBean> callback);
    }

}