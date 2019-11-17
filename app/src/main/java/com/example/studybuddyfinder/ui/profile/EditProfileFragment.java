package com.example.studybuddyfinder.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.studybuddyfinder.R;

import java.io.File;

public class EditProfileFragment extends Fragment {
    ImageButton changeImage;
    ImageView profilePic;
    EditText nameText, gradeText, majorText;
    private static final int GALLERY_REQUEST_CODE = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameText = view.findViewById(R.id.nameText);
        gradeText = view.findViewById(R.id.gradeText);
        majorText = view.findViewById(R.id.majorText);

        String name =  nameText.getText().toString();
        String grade =  gradeText.getText().toString();
        String major =  majorText.getText().toString();

        profilePic = (ImageView) view.findViewById(R.id.profilePic);
        changeImage = (ImageButton) view.findViewById(R.id.changeImage);

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
    }
}