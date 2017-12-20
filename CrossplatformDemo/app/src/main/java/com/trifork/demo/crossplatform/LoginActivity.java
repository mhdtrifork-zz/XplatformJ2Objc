package com.trifork.demo.crossplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xplatformdemo.libsharedcode.NetworkingHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(R.string.login_form_title);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkingHelper helper = new NetworkingHelper();
                try {
                    helper.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        findViewById(R.id.create_user_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, CreateUserActivity.class));
            }
        });
    }
}

