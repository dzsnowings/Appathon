package com.example.studybuddyfinder.ui.browse;

import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

        final View viewfinal = view;
        final ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        FirebaseFirestore.getInstance().collection("Groups").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("GG", "Entered Loop");
                        for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                            Log.d("GG", "New Group");
                            final ArrayList<String> group = new ArrayList<>();
                            group.add(snap.getString("Class"));
                            group.add(snap.getString("Location"));
                            group.add(snap.getString("Time"));
                            group.add("Custom Message");
                            groups.add(group);
                        }
                        Log.d("GG", (new Integer(groups.size())).toString());
                        RecyclerView recyclerView = viewfinal.findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseFragment.this.getContext()));
                        adapter = new RecyclerViewAdapter(BrowseFragment.this.getContext(), groups);
                        recyclerView.setAdapter(adapter);
                    }
                });



    }
    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this.getContext(), "You clicked " + adapter.getItem(position) + " on row number" + position, Toast.LENGTH_SHORT).show();
    }
}
