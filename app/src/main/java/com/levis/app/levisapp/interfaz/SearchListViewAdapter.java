package com.levis.app.levisapp.interfaz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.levis.app.levisapp.R;
import com.levis.app.levisapp.mundo.Imagen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Japo on 17/09/17.
 */

public class SearchListViewAdapter extends ArrayAdapter<Imagen> {

    private Context context;
    private List<Imagen> imageList;

    //Constructor
    public SearchListViewAdapter(Context _context,int resource, ArrayList<Imagen> _imageList) {
        super(_context, resource, _imageList);
        context = _context;
        imageList = _imageList;
    }



    public View getView(int position, View convertView, ViewGroup parent){

        //get the image we are displaying
        Imagen imagenTemp = imageList.get(position);

        //get the inflater and inflate the XML laysout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //conditionally inflate either standard or special template
        View view;
        view = inflater.inflate(R.layout.explore_list_layout, null);

        //Conect Code with layout objects
        ImageView imgLoad = (ImageView) view.findViewById(R.id.image_load);
        TextView tittleLoad = (TextView) view.findViewById(R.id.tittle_load);
        TextView dateLoad = (TextView) view.findViewById(R.id.date_load);
        TextView ownerLoad = (TextView) view.findViewById(R.id.owner_load);
        TextView locationLoad = (TextView) view.findViewById(R.id.location_load);


        //Receive Image path
        File imgFile = new  File(imageList.get(position).getImagenCargada());

        //Check that the file exists
        if(imgFile.exists()){

            //Set the image to a bitmap
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            //Assing the bitmap to the imageView
            //imgLoad.setImageResource(myBitmap);
            imgLoad.setImageBitmap(myBitmap);
        }

        //Set the rest of the texts
        tittleLoad.setText(imageList.get(position).getTitulo());
        dateLoad.setText(imageList.get(position).getFechaCarga());
        ownerLoad.setText(imageList.get(position).getNombreUsuario());
        locationLoad.setText(imageList.get(position).getUbicacion());

        return view;
    }


}
