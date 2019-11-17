package com.example.studybuddyfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameText = findViewById(R.id.loginUsername);
        final EditText passwordText = findViewById(R.id.loginPassword);
        final Button loginButton = findViewById(R.id.loginLoginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                if (!password.equals(password)) {
                    String message = "Username and/or password is incorrect. Please try again.";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!(password.equals("") && username.equals(""))) {
                    String message = "You did not enter username or password";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    String message = "Successfully logged in.";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
