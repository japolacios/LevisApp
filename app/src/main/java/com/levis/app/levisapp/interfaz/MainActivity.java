package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.SessionManagement;

public class MainActivity extends AppCompatActivity {

    private SessionManagement session;
    private Button iniciarSesion;
    private EditText nombre, contraseña;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Session Manager
        session = new SessionManagement(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        iniciarSesion = (Button) findViewById(R.id.loginButton);
        nombre = (EditText) findViewById(R.id.userName);
        contraseña = (EditText) findViewById(R.id.loginPassword);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String password = contraseña.getText().toString();
                session.createLoginSession(name, password);
                //Aqui especifico a traves del objeto intent actual se activara la pantalla juego

                Intent intent = new Intent(MainActivity.this, Search.class);
                HWDPrincipal principal=new HWDPrincipal();
                intent.putExtra("PRINCIPAL", principal);

                //Se ordena que se inicie la otra actividad
                startActivity(intent);
                finish();
            }
        });


    }

}

