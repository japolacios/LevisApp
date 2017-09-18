package com.levis.app.levisapp.interfaz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.SessionManagement;

public class Register extends Activity {

    private EditText nombre,contraseña;
    Button registrar;
    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Session Manager
        session = new SessionManagement(getApplicationContext());

        registrar=(Button)findViewById(R.id.registerButton);
        nombre=(EditText)findViewById(R.id.name);
        contraseña=(EditText)findViewById(R.id.password);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombree = nombre.getText().toString();
                String pass=contraseña.getText().toString();

                //Agregar BD

                //Iniciar sesion
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




    }
}
