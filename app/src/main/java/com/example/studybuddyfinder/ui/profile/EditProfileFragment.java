package com.example.studybuddyfinder.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.studybuddyfinder.ContentActivity;
import com.example.studybuddyfinder.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;

public class EditProfileFragment extends Fragment {
    private static final int GALLERY_REQUEST_CODE = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText nameText = view.findViewById(R.id.editNameText);
        EditText gradeText = view.findViewById(R.id.editGradeText);
        EditText majorText = view.findViewById(R.id.editMajorText);
        Button saveButton = view.findViewById(R.id.saveButton);

        //String name =  nameText.getText().toString();
        String grade =  gradeText.getText().toString();
        String major =  majorText.getText().toString();

        ImageView profilePic = (ImageView) view.findViewById(R.id.profilePic);
        ImageButton changeImage = (ImageButton) view.findViewById(R.id.changeImage);

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                Intent photoGallery = new Intent(Intent.ACTION_PICK);

                File gallery = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                String galleryPath = gallery.getPath();

                Uri data = Uri.parse(galleryPath);

                photoGallery.setDataAndType(data, "image/*");

                startActivityForResult(photoGallery, GALLERY_REQUEST_CODE);
            }

            /*
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == Activity.RESULT_OK) {
                    if (requestCode == GALLERY_REQUEST_CODE) {
                        Uri image = data.getData();
                        String[] filePath = { MediaStore.Images.Media.DATA };
                        Cursor cursor = getContentResolver().query(image, filePath, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePath[0]);
                        String imgDecodableString = cursor.getString(columnIndex);
                        cursor.close();
                        profilePic.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    }

                }
            }
             */
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = nameText.getText().toString();
                FirebaseFirestore.getInstance().collection("Users").document(ContentActivity.key).update(
                        "First", name
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("GG", "Update successful for " + ContentActivity.key);
                    }
                });
                Fragment profileView = new ProfileFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, profileView);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}
