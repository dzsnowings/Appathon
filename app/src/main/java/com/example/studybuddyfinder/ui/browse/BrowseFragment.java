package com.example.studybuddyfinder.ui.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddyfinder.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    RecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button createGroupButton = view.findViewById(R.id.createGroupButton);

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment createGroupFragment = new CreateGroupFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, createGroupFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < CreateGroupFragment.getNumGroups(); i++) {
            groups.add(new ArrayList<String>());
            groups.get(i).add(CreateGroupFragment.getClassName());
            groups.get(i).add("location" + i);
            groups.get(i).add("time" + i);
            groups.get(i).add("type" + i);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new RecyclerViewAdapter(this.getContext(), groups);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this.getContext(), "You clicked " + adapter.getItem(position) + " on row number" + position, Toast.LENGTH_SHORT).show();
    }
}