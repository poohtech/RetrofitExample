package com.esp.retrofitexample.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esp.retrofitexample.API.RegistrationAPI;
import com.esp.retrofitexample.Bean.RegistrationBean;
import com.esp.retrofitexample.R;
import com.esp.retrofitexample.Utils.Configs;
import com.esp.retrofitexample.Utils.ResponseListener;

public class RegistrationActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText edtUsername, edtEmail, edtPassword;
    private Button btnSignup;
    private RegistrationBean myBean;
    private RegistrationAPI registrationAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        progressDialog = new ProgressDialog(RegistrationActivity.this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                registrationAPI = new RegistrationAPI(RegistrationActivity.this, responseListener, edtUsername.getText().toString(), edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
            }
        });
    }

    public ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponce(String tag, int result, Object obj) {
            progressDialog.dismiss();
            if (tag.equals(Configs.TAG_REGISTER) && result == Configs.RESULT_OK) {
                myBean = (RegistrationBean) obj;

                System.out.println("--------responseListener-------");
                System.out.println("--------responseListener : myBean.user_id-------" + myBean.user_id);
                System.out.println("--------responseListener : myBean.avatar-------" + myBean.avatar);
                System.out.println("--------responseListener : myBean.wallpaper-------" + myBean.wallpaper);
                Intent intent = new Intent(RegistrationActivity.this, RouteActivity.class);
                startActivity(intent);

            }
            registrationAPI = null;
        }
    };

}
