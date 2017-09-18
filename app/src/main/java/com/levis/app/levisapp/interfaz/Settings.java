package com.levis.app.levisapp.interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.levis.app.levisapp.R;

public class Settings extends AppCompatActivity {

    private Button changePassword;
    private Button logout;
    private EditText pass;
    private EditText confirmPass;

    public Settings() {
        iniciarComponentes();
        escucharComponentes();
    }

    public void iniciarComponentes(){
        changePassword=(Button)findViewById(R.id.botonChangePassword);
        logout=(Button)findViewById(R.id.botonLogout);
        pass=(EditText)findViewById(R.id.newPassword);
        confirmPass=(EditText)findViewById(R.id.confirmPassword);
    }
    public void escucharComponentes(){
        changePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String n = String.valueOf(pass.getText());
                String c = String.valueOf(confirmPass.getText());
                if(n.equals(c)){
                    Context context = getApplicationContext();
                    CharSequence text = "Su contraseña ha sido cambiada";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    cambiarContraseña(n);
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Por favor escriba la misma contraseña en ambos campos";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }


            }});
    }

    private void cambiarContraseña(String a) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //Change to Profile View
    public void goBackToSearch(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
