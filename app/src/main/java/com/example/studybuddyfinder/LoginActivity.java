package com.example.studybuddyfinder;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        EditText usernameText = findViewById(R.id.username);
        EditText passwordText = findViewById(R.id.password);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
    }
}
