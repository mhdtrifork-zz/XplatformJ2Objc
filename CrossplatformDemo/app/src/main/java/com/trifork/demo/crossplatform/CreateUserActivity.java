package com.trifork.demo.crossplatform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import com.xplatformdemo.libsharedcode.NetworkingHelper;
import com.xplatformdemo.libsharedcode.StringVerification;
import com.xplatformdemo.libsharedcode.Verified;

public class CreateUserActivity extends AppCompatActivity {

    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.repeat_password).setVisibility(View.VISIBLE);
        findViewById(R.id.login_btn).setVisibility(View.GONE);
        errorTextView = findViewById(R.id.error_text);

        findViewById(R.id.create_user_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameField = findViewById(R.id.user_name);
                EditText passwordField = findViewById(R.id.password);
                EditText repeatPasswordField = findViewById(R.id.repeat_password);
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String repeatPassword = repeatPasswordField.getText().toString();

                StringVerification verify = new StringVerification();
                Verified usernameIsValid = verify.verifyUsername(username);
                if (usernameIsValid.isValid()) {
                    Verified passwordIsValid = verify.verifyPassword(password);
                    if (passwordIsValid.isValid()) {
                        Verified passwordsAreIdentical = StringVerification.compareStrings(password, repeatPassword);
                        if (passwordsAreIdentical.isValid()) {
                            errorTextView.setVisibility(View.INVISIBLE);
                            //call backend
                            try {
                                NetworkingHelper backend = new NetworkingHelper();
                                if (backend.createUser(username, password)) {
                                    errorTextView.setText("Succes");
                                    errorTextView.setVisibility(View.VISIBLE);
                                    CreateUserActivity.this.finish();
                                } else {
                                    errorTextView.setText("Der eksisterer allerede en bruger med dette navn");
                                    errorTextView.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                errorTextView.setText("Der skete en fejl under forbindelsen med serveren");
                                errorTextView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            errorTextView.setText(passwordsAreIdentical.errorMsg());
                            errorTextView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        errorTextView.setText(passwordIsValid.errorMsg());
                        errorTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    errorTextView.setText(usernameIsValid.errorMsg());
                    errorTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

