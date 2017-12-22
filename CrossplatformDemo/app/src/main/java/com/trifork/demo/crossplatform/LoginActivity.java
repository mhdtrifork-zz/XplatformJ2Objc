package com.trifork.demo.crossplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
                TextView errorTextView = findViewById(R.id.error_text);
                EditText usernameField = findViewById(R.id.user_name);
                EditText passwordField = findViewById(R.id.password);
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                NetworkingHelper helper = new NetworkingHelper();
                try {
                    if (helper.login(username, password)) {
                        errorTextView.setVisibility(View.INVISIBLE);
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    errorTextView.setText("Fejl");
                    errorTextView.setVisibility(View.VISIBLE);
                }
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

