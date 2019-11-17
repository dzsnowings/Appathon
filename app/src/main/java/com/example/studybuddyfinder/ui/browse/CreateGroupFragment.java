package com.example.studybuddyfinder.ui.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studybuddyfinder.R;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstantState) {
        List<String> SpinnerArray = new ArrayList<String>();
        SpinnerArray.add("AM");
        SpinnerArray.add("PM");

        List<String> typeArray = new ArrayList<String>();
        typeArray.add("Test");
        typeArray.add("Homework");
        typeArray.add("Study");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, SpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, typeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) view.findViewById(R.id.timeSpinner);
        spinner1.setAdapter(adapter);

        Spinner typeSpinner = (Spinner) view.findViewById(R.id.typeText);
        typeSpinner.setAdapter(typeAdapter);

        spinner1.setOnItemSelectedListener(this);
        typeSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

