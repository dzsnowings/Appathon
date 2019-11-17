package com.example.studybuddyfinder.ui.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddyfinder.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String userId = UUID.randomUUID().toString();

    private RecyclerView rvMessages;
    private MessageAdapter messageAdapter;
    private List<Message> messages;

    private EditText etMessage;
    private ImageButton imageBtn;

    private Message currMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);

        rvMessages = findViewById(R.id.recyclerView);
        etMessage = findViewById(R.id.messageEditText);
        imageBtn = findViewById(R.id.imageButton);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(messages);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(messageAdapter);

        FirebaseFirestore.getInstance().collection("messages")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e("MainActivity", "Firebase Cloud Firestore - Initial listener", e);
                            return;
                        }

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Message newMessage = doc.toObject(Message.class);
                            if (!messages.contains(newMessage)) {
                                messages.add(newMessage);
                            }
                        }

                        Collections.sort(messages, new Comparator<Message>() {
                            @Override
                            public int compare(Message o1, Message o2) {
                                return Long.compare(o1.getTime(), o2.getTime());
                            }
                        });

                        messageAdapter.notifyDataSetChanged();
                        rvMessages.smoothScrollToPosition(messages.size());
                    }
                });

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etMessage.getText().toString();
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();

                Message tempMessage = new Message(UUID.randomUUID().toString(),
                        content,
                        "foo",
                        System.currentTimeMillis()
                );

                currMessage = tempMessage;
                FirebaseFirestore.getInstance()
                        .collection("messages")
                        .document(currMessage.getId())
                        .set(currMessage)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                etMessage.setText("");
                            }
                        });
            }
        });
    }
}
