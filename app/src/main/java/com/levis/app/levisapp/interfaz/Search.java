package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.Imagen;
import com.levis.app.levisapp.mundo.SessionManagement;

import java.util.ArrayList;
import java.util.List;

import static com.levis.app.levisapp.R.id.imageView;

public class Search extends AppCompatActivity {


    //Set ListView Components
    ListView listView;
    List list = new ArrayList();
    SearchListViewAdapter adapter;
    ArrayList<Imagen> imageList;

    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        session = new SessionManagement(getApplicationContext());

        session.checkLogin();

        imageList = new ArrayList<Imagen>();

        //Add items in the meantime
        Imagen temp1 = new Imagen("John Doe","Valle","01/01/99","Mi Titulo","@DrawableRes/explire_dummie.png");
        Imagen temp2 = new Imagen("Benito Camelas","Hell","01/01/99","Mi Titulo2","@DrawableRes/explire_dummie.png");

        imageList.add(temp1);
        imageList.add(temp2);

        ArrayAdapter<Imagen> adapter = new SearchListViewAdapter(this, 0, imageList);
        //Find list view and bind it with the custom adapter
        listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(adapter);

    }

    //Start the camera to take a picture
    public void openCamera(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i , 0);
    }
    //What to do after the picture is taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if (resultData != null) {

            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            int column_index_data = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToLast();

            String imagePath = cursor.getString(column_index_data);
            Bitmap bitmapImage = BitmapFactory.decodeFile(imagePath);
            Log.e("DEV", "Se supone que el path es este: " + imagePath);
            //imageView.setImageBitmap(bitmapImage );

            //---------------------------------------------------------
            //Start photo activity to put tittle, must pass the image path
            //---------------------------------------------------------
            Intent intentPhoto = new Intent(this, Photo.class);
            String photoPath = imagePath;
            intentPhoto.putExtra("photoPath", photoPath);
            startActivity(intentPhoto);

        }
    }

    //Change to Profile View
    public void goToProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    //Change to Settings View
    public void goToSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
