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
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;
import com.levis.app.levisapp.mundo.Usuario;

public class Settings extends AppCompatActivity {

    private Button changePassword;
    private Button logout;
    private EditText pass;
    private EditText confirmPass;
    private SessionManagement session;
    private HWDPrincipal princi;
    private Usuario user;
    private LogicDataBase db;

    public Settings() {
        iniciarComponentes();
        escucharComponentes();
        princi=new HWDPrincipal();
        user=new Usuario();
        db = new LogicDataBase(this);
        session=new SessionManagement(getApplicationContext());
        session.checkLogin();
        String[] sp1 = session.getDetallesUsuario();
        user = db.buscarUsuario(sp1[0]);
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
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent jugar = new Intent(Settings.this, MainActivity.class);
                startActivity(jugar);
                cerrarSesion();

            }


        });
    }

    private void cambiarContraseña(String a) {
        user.setUsuPassword(a);
        db.cambiarClave(user);
    }
    private void cerrarSesion(){
        session.logoutUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        escucharComponentes();
        iniciarComponentes();
    }

    //Change to Profile View
    public void goBackToSearch(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
