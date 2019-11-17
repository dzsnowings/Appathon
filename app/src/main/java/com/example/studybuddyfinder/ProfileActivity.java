package com.example.studybuddyfinder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {
    ImageButton changeImage;
    ImageView profilePic;
    EditText nameText, gradeText, majorText;
    private static final int GALLERY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.editNameText);
        gradeText = findViewById(R.id.gradeText);
        majorText = findViewById(R.id.majorText);

        String name =  nameText.getText().toString();
        String grade =  gradeText.getText().toString();
        String major =  majorText.getText().toString();

        profilePic = (ImageView) findViewById(R.id.profilePic);
        changeImage = (ImageButton) findViewById(R.id.changeImage);

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
            });
        }
}
