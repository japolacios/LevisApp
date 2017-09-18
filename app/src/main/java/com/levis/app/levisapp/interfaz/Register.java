package com.levis.app.levisapp.interfaz;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.Bitmap;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.Imagen;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;
import com.levis.app.levisapp.mundo.Usuario;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register extends AppCompatActivity implements IPickResult {

    private EditText nombre,contraseña, correo;
    Button registrar;
    Button imagenBoton;
    // Session Manager Class
    SessionManagement session;
    private LogicDataBase db;
    private String imagenPerfil = "";
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

                if(!imagenPerfil.isEmpty()){
                    Imagen img = new Imagen();
                    img.setNombreUsuario(nombree);
                    DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
                    String date = df.format(Calendar.getInstance().getTime());
                    img.setFechaCarga(date);
                    img.setTitulo("imagenPerfil");
                    img.setCorreoUsuario(email);
                    img.setImagenCargada(imagenPerfil);
                    img.setUbicacion("");

                    Toast.makeText(getApplicationContext(), "Ruta perfil: "+imagenPerfil, Toast.LENGTH_SHORT).show();
                    Log.d("Direccion", imagenPerfil);


                    user.setImagenPerfil(img);

                    db.insertarUsuario(user);
                    db.insertarImagen(img);

                }else{
                    db.insertarUsuario(user);
                }

                session.createLoginSession(email,pass);
                Intent intent=new Intent(Register.this,Search.class);

                HWDPrincipal princi = new HWDPrincipal();
                intent.putExtra("PRINCIPAL", princi);
                intent.putExtra("USUARIO", user);
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
        PickSetup setup = new PickSetup()
                .setTitle("Seleccionar")
                .setTitleColor(Color.parseColor("#212121"))
                .setBackgroundColor(Color.WHITE)
                .setProgressText("Cargando...")
                .setProgressTextColor(Color.parseColor("#212121"))
                .setCancelText("Cancelar")
                .setCancelTextColor(Color.parseColor("#212121"))
                .setButtonTextColor(Color.parseColor("#212121"))
                .setDimAmount((float)0.3)
                .setFlip(true)
                .setMaxSize(500)
                .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                .setCameraButtonText("Camara")
                .setGalleryButtonText("Galeria")
                .setIconGravity(Gravity.LEFT)
                .setButtonOrientation(0)
                .setSystemDialog(false);

        PickImageDialog.build(setup).show(this);
    }

    @Override
    public void onPickResult(final PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            imagenPerfil = r.getPath();

            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


