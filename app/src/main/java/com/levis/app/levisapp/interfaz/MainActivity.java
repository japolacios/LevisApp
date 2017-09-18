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
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;

public class MainActivity extends AppCompatActivity {

    private SessionManagement session;
    private Button iniciarSesion;
    private EditText nombre, contraseña;
    private LogicDataBase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new LogicDataBase(this);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

        iniciarSesion = (Button) findViewById(R.id.loginButton);
        nombre = (EditText) findViewById(R.id.userName);
        contraseña = (EditText) findViewById(R.id.loginPassword);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String password = contraseña.getText().toString();
                //Validate that the Fields are not empty
                if(name != null && !name.equals("") && password != null && !password.equals("")){

                    session.createLoginSession(name, password);

                    String storedPassword=db.getSingleEntry(name);


                    if(password.equals(storedPassword))
                    {
                        Toast.makeText(getApplicationContext(), "Bienvenido de nuevo!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, Search.class);
                        HWDPrincipal principal=new HWDPrincipal();
                        intent.putExtra("PRINCIPAL", principal);

                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "El Usuario o Contraseña no coinciden", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(getApplicationContext(), "Por favor, introduzca el Usuario y la Contraseña", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
        //Change to Register View
    public void signIn(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}

