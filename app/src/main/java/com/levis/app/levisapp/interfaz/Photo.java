package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.Imagen;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;

import java.io.File;

public class Photo extends AppCompatActivity {

    String path;
    ImageView imgDisplay;
    EditText nombre;
    SessionManagement session;
    LogicDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        session = new SessionManagement(getApplicationContext());
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        path = (String) intent.getSerializableExtra("photoPath");

        //Set the image that just arrived to the bg of the view
        imgDisplay = (ImageView) findViewById(R.id.imagebackground);
        nombre = (EditText) findViewById(R.id.editText2);
        imgDisplay.setImageBitmap(BitmapFactory.decodeFile(path));
        db = new LogicDataBase(getApplicationContext());

    }

    public void publicar(View v){
        if(nombre.getText().toString().isEmpty()){
            // // TODO: 19/09/17 toast de validacion
        }else{
            Imagen imagen = new Imagen();
            imagen.setNombreUsuario(nombre.getText().toString());
            imagen.setImagenCargada(path);
            imagen.setCorreoUsuario(session.getDetallesUsuario()[0]);
            imagen.setFechaCarga("A");
            imagen.setUbicacion("B");
            imagen.setTitulo("C");
            db.insertarImagen(imagen);

            Intent intent = new Intent(this, Search.class);
            startActivity(intent);
        }

    }
}
