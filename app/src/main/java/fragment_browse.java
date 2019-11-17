package com.example.studybuddyfinder;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddyfinder.CustomDialog;
import com.example.studybuddyfinder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;

public class fragment_browse extends AppCompatActivity implements CustomDialog.OnInputListener {
    private RecyclerView mainScreenTaskNameList;
    private ArrayList<String> taskNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainScreenTaskNameList = findViewById(R.id.task_name_list);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        mainScreenTaskNameList.setLayoutManager(manager);

        TaskAdapter adapter = new TaskAdapter(taskNames);
        mainScreenTaskNameList.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem() {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(), "CustomDialog");
    }

    @Override
    public void sendInput(String input) {
        taskNames.add(input);
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        mainScreenTaskNameList.getAdapter().notifyItemInserted(taskNames.size());
    }

}

