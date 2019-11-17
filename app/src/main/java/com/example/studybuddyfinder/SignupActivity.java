package com.example.studybuddyfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


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
            public void onClick (View v){
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
                if (!password.equals(confirm) || firstName.equals("") || lastName.equals("") || email.equals("") || username.equals("") || password.equals("")) {
                    String error = "Some required information is missing.";
                    Toast.makeText(SignupActivity.this, error, Toast.LENGTH_SHORT).show();
                }
                else {

                    Map<String, String> user = new HashMap<>();
                    user.put("First", firstName);
                    user.put("Last", lastName);
                    user.put("Email", email);
                    user.put("Phone", phone);
                    user.put("Username", username);
                    user.put("Password", password);

                    final String key = Integer.toHexString(username.hashCode() + password.hashCode());
                    // Add a new document with a generated ID
                    FirebaseFirestore.getInstance().collection("Users")
                            .document(key)
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("GG", "DocumentSnapshot added with ID: " + key);
                                    Intent intent = new Intent(SignupActivity.this, ContentActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("GG", "Error adding document", e);
                                    String error = "There was an error signing you up.";
                                    Toast.makeText(SignupActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });
    }
}

