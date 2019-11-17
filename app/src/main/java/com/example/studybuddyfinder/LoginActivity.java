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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
                } else if (password.equals("") || username.equals("")) {
                    String message = "You did not enter username or password";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseFirestore.getInstance().collectionGroup("Users").whereEqualTo("Username", username).whereEqualTo("Password", password)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d("GG", "Started");
                                int count = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    count++;
                                    Log.d("GG", document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                                    startActivity(intent);
                                }
                                if(count == 0) {
                                Log.d("GG", "REJECTED");
                                String message = "You have not signed up yet.";
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show(); }
                            } else {
                                Log.d("GG", "Error getting documents: ", task.getException());
                                String message = "There was an error connecting.";
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}
