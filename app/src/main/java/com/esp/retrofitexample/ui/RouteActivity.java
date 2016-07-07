package com.esp.retrofitexample.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.esp.retrofitexample.API.MyRoutAPI;
import com.esp.retrofitexample.Bean.GetRoutBean;
import com.esp.retrofitexample.R;
import com.esp.retrofitexample.Utils.Configs;
import com.esp.retrofitexample.Utils.ResponseListener;

public class RouteActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextView txtresponse;
    private GetRoutBean myBean;
    private MyRoutAPI myRoutAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtresponse = (TextView) findViewById(R.id.txtresponse);
        progressDialog = new ProgressDialog(RouteActivity.this);
        progressDialog.show();
        myRoutAPI = new MyRoutAPI(RouteActivity.this, responseListener, "23.022505,72.571362", "22.307159,73.181219");

    }

    public ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponce(String tag, int result, Object obj) {
            progressDialog.dismiss();
            if (tag.equals(Configs.TAG_GETROUT) && result == Configs.RESULT_OK) {
                myBean = (GetRoutBean) obj;

                if (myBean.routes.get(0).legs.get(0).steps.size() > 0) {
                    for (int k = 0; k < myBean.routes.get(0).legs.get(0).steps.size(); k++) {
                        txtresponse.setText(txtresponse.getText().toString() + myBean.routes.get(0).legs.get(0).steps.get(k).start_location.lat + ":::" + myBean.routes.get(0).legs.get(0).steps.get(k).start_location.lng + "\n");
                    }
                }
            }
            myRoutAPI = null;
        }
    };

}
