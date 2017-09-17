package com.levis.app.levisapp.interfaz;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.Imagen;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {


    //Set ListView Components
    ListView listView;
    List list = new ArrayList();
    SearchListViewAdapter adapter;
    ArrayList<Imagen> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        imageList = new ArrayList<Imagen>();





        //Add items in the meantime
        Imagen temp1 = new Imagen("John Doe","Valle","01/01/99","Mi Titulo","@DrawableRes/explire_dummie.png");
        Imagen temp2 = new Imagen("Benito Camelas","Hell","01/01/99","Mi Titulo2","@DrawableRes/explire_dummie.png");





        imageList.add(temp1);
        imageList.add(temp2);

        ArrayAdapter<Imagen> adapter = new SearchListViewAdapter(this, 0, imageList);
        //Find list view and bind it with the custom adapter
        listView = (ListView) findViewById(R.id.main_list);
        listView.setAdapter(adapter);

    }

}
