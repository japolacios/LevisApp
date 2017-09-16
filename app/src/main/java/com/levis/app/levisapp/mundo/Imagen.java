package com.levis.app.levisapp.mundo;

import android.media.Image;

/**
 * Created by juancamilovilladagamboa on 16/09/17.
 */

public class Imagen {

    private String nombreUsuario;
    private String ubicacion;
    private String fechaCarga;
    private String titulo;
    private Image imagenCargada;

    public Imagen(){
        nombreUsuario = "";
        ubicacion = "";
        fechaCarga = "";
        titulo = "";
        imagenCargada = null;
    }

    public Imagen(String nomUs, String ub, String fC, String tit, Image img){
        nombreUsuario = nomUs;
        ubicacion = ub;
        fechaCarga = fC;
        titulo = tit;
        imagenCargada  = img;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public String getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Image getImagenCargada() {
        return imagenCargada;
    }

    public void setImagenCargada(Image imagenCargada) {
        this.imagenCargada = imagenCargada;
    }

}
