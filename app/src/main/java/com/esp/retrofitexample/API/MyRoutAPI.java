package com.esp.retrofitexample.API;

import android.content.Context;

import com.esp.retrofitexample.Bean.GetRoutBean;
import com.esp.retrofitexample.Utils.Configs;
import com.esp.retrofitexample.Utils.ResponseListener;
import com.esp.retrofitexample.Utils.Utils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by hardikjani on 6/29/16.
 */
public class MyRoutAPI {


    public static final String appendURL = "/directions/json?sensor=false&mode=driving";

    public MyRoutAPI(Context context, final ResponseListener responseListener, String origin, String destination) {

        GetRoutAPI apiMethod = Utils.getAdapter().create(GetRoutAPI.class);

        apiMethod.getBean(origin, destination, new Callback<GetRoutBean>() {
            @Override
            public void success(GetRoutBean getRoutBean, Response response) {

                if (response.getStatus() == 200) {
                    responseListener.onResponce(Configs.TAG_GETROUT, Configs.RESULT_OK, getRoutBean);
                } else {
                    responseListener.onResponce(Configs.TAG_GETROUT, Configs.RESULT_FAIL, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                responseListener.onResponce(Configs.TAG_GETROUT, Configs.RESULT_FAIL, null);
            }
        });
    }

    public interface GetRoutAPI {
        @GET(appendURL)
        void getBean(@Query("origin") String param1, @Query("destination") String param2, Callback<GetRoutBean> callback);
    }

    /*https://maps.googleapis.com/maps/api/directions/json?origin=25.0225&destination=72.5732&sensor=false&mode=driving*/
}