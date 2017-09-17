package com.levis.app.levisapp.interfaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.levis.app.levisapp.R;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {


    //Set ListView Components
    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get listView By Id
        listView = (ListView) findViewById(R.id.main_list);


        //Add items in the meantime
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");
        list.add("HolaQueHace");


        //Load layout and data to listView
        adapter = new ArrayAdapter(Search.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

}
