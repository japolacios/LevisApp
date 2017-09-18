package com.levis.app.levisapp.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.levis.app.levisapp.R;

public class Settings extends AppCompatActivity {

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
