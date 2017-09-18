package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.levis.app.levisapp.R;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
}
