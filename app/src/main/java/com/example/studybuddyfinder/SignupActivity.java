package com.example.studybuddyfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText firstNameText = findViewById(R.id.firstName);
        final EditText lastNameText = findViewById(R.id.lastName);
        final EditText emailText = findViewById(R.id.email);
        final EditText phoneText = findViewById(R.id.phone);
        final EditText usernameText = findViewById(R.id.signupUsername);
        final EditText passwordText = findViewById(R.id.signupPassword);
        final EditText confirmText = findViewById(R.id.confirmPassword);
        final Button signupButton = findViewById(R.id.signupSignupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();
                String email = emailText.getText().toString();
                String phone = phoneText.getText().toString();
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String confirm = confirmText.getText().toString();
                
                if (!password.equals(confirm)) {
                    String error = "Passwords do not match.";
                    Toast.makeText(SignupActivity.this, error, Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(SignupActivity.this, ContentActivity.class);
                startActivity(intent);
            }
        });
    }
}
