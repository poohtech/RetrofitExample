package com.esp.retrofitexample.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esp.retrofitexample.API.LoginAPI;
import com.esp.retrofitexample.Bean.LoginBean;
import com.esp.retrofitexample.R;
import com.esp.retrofitexample.Utils.Configs;
import com.esp.retrofitexample.Utils.ResponseListener;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnSignUp;
    private LoginBean myBean;
    private LoginAPI loginAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        progressDialog = new ProgressDialog(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                loginAPI = new LoginAPI(LoginActivity.this, responseListener, edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }

    public ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponce(String tag, int result, Object obj) {
            progressDialog.dismiss();
            if (tag.equals(Configs.TAG_LOGIN) && result == Configs.RESULT_OK) {
                myBean = (LoginBean) obj;

                System.out.println("--------responseListener-------");
                System.out.println("--------responseListener : myBean.user_id-------" + myBean.user_id);
                System.out.println("--------responseListener : myBean.username-------" + myBean.username);
                System.out.println("--------responseListener : myBean.email-------" + myBean.email);
                Intent intent = new Intent(LoginActivity.this, RouteActivity.class);
                startActivity(intent);

            }
            loginAPI = null;
        }
    };

}
