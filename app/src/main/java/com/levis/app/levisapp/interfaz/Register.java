package com.levis.app.levisapp.interfaz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;
import com.levis.app.levisapp.mundo.Usuario;

public class Register extends Activity {

    private EditText nombre,contraseña, correo;
    Button registrar;
    Button imagenBoton;
    // Session Manager Class
    SessionManagement session;
    private LogicDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

        db = new LogicDataBase(this);

        registrar=(Button)findViewById(R.id.registerButton);
        nombre=(EditText)findViewById(R.id.name);
        correo=(EditText)findViewById(R.id.mail);
        contraseña=(EditText)findViewById(R.id.password);
        imagenBoton=(Button)findViewById(R.id.botonimagen) ;

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombree = nombre.getText().toString();
                String email = correo.getText().toString();
                String pass=contraseña.getText().toString();

                Usuario user = new Usuario();
                user.setNombreUsuario(nombree);
                user.setCorreoElectronico(email);
                user.setUsuPassword(pass);

                db.insertarUsuario(user);

                session.createLoginSession(nombree,pass);
                Intent intent=new Intent(Register.this,Search.class);

                HWDPrincipal princi = new HWDPrincipal();
                intent.putExtra("PRINCIPAL", princi);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //Se ordena que se inicie la otra actividad
                startActivity(intent);
                finish();


            }
        });

        imagenBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i , 0);
            }
        });



    }
    //Start the camera to take a picture
    public void openCamera(View view) {

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
            //Log.e("DEV", "Se supone que el path es este: " + imagePath);
            //imageView.setImageBitmap(bitmapImage );

            System.out.println(bitmapImage);


        }
    }
}


