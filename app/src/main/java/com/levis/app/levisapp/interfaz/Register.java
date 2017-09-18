package com.levis.app.levisapp.interfaz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.Imagen;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;
import com.levis.app.levisapp.mundo.Usuario;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Register extends Activity {

    private EditText nombre,contraseña, correo;
    Button registrar;
    Button imagenBoton;
    // Session Manager Class
    SessionManagement session;
    private LogicDataBase db;
    private ArrayList<Image> images = new ArrayList<>();

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
        imagenBoton=(Button)findViewById(R.id.botonimagen);

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

                if(images.size()>0){
                    Imagen img = new Imagen();
                    img.setNombreUsuario(nombree);
                    DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
                    String date = df.format(Calendar.getInstance().getTime());
                    img.setFechaCarga(date);
                    img.setTitulo("imagenPerfil");
                    img.setCorreoUsuario(email);
                    Image img1 = images.get(0);
                    ByteBuffer buffer = img1.getPlanes()[0].getBuffer();
                    byte[] bytes = new byte[buffer.capacity()];
                    img.setImagenTabla();
                }

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
                start();
            }
        });



    }

    private void start() {
        ImagePicker.with(this)
                .setFolderMode(true)
                .setCameraOnly(false)
                .setFolderTitle("Album")
                .setMultipleMode(false)
                .setSelectedImages(images)
                .setMaxSize(10)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


