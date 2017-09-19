package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.HWDPrincipal;
import com.levis.app.levisapp.mundo.Imagen;
import com.levis.app.levisapp.mundo.LogicDataBase;
import com.levis.app.levisapp.mundo.SessionManagement;
import com.levis.app.levisapp.mundo.Usuario;

import java.util.List;
import java.util.Set;

public class Profile extends AppCompatActivity {

    private Button editProfile;
    private GridView gridProfile;
    private SessionManagement session;
    private HWDPrincipal princi;
    private Usuario user;
    private LogicDataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

    private void iniciarComponentes() {
        editProfile=(Button)findViewById(R.id.buttonEditProfile);
        gridProfile=(GridView)findViewById(R.id.gridViewProfile);
        insertarImagenes();

    }

    private void insertarImagenes() {
        
    }

    private void escucharComponentes() {
        editProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent jugar = new Intent(Profile.this, Settings.class);
                startActivity(jugar);

            }


        });
    }

    //Change to Profile View
    public void goBackToSearch(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    //Change to Settings View
    public void goToSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public List<Imagen> darTodasImagenes(){
        return db.todasLasImagenes();
    }
}
