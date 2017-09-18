package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.levis.app.levisapp.R;

import java.io.File;

public class Photo extends AppCompatActivity {

    String path;
    ImageView imgDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        path = (String) intent.getSerializableExtra("photoPath");

        //Set the image that just arrived to the bg of the view
        imgDisplay = (ImageView) findViewById(R.id.imagebackground);
        imgDisplay.setImageBitmap(BitmapFactory.decodeFile(path));

    }
}
