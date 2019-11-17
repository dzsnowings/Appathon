package com.example.studybuddyfinder.ui.browse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studybuddyfinder.ContentActivity;
import com.example.studybuddyfinder.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CreateGroupFragment extends Fragment {
    private static EditText className;
    private static int numGroups = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstantState) {
        List<String> SpinnerArray = new ArrayList<String>();
        SpinnerArray.add("AM");
        SpinnerArray.add("PM");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, SpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) view.findViewById(R.id.timeSpinner);
        spinner.setAdapter(adapter);
        className = view.findViewById(R.id.classText);
        Button makeGroup = view.findViewById(R.id.createButton);
        makeGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> group = new HashMap<>();
                group.put("Class", className.getText().toString());
                group.put("Members", Arrays.asList(ContentActivity.key));

                FirebaseFirestore.getInstance().collection("Groups")
                        .document(className.getText().toString()).set(group)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("GG", "Successfully added group");
                                numGroups++;
                            }
                        });
            }
            });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(CreateGroupFragment.this.getActivity(), item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(CreateGroupFragment.this.getActivity(), "Selected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });
    }

    public static String getClassName() {
        return className.getText().toString();
    }

    public static int getNumGroups() {
        return numGroups;
    }
}
