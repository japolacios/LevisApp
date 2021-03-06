package com.levis.app.levisapp.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by juancamilovilladagamboa on 16/09/17.
 */

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String correoElectronico;
    private String usuPassword;
    private Imagen imagenPerfil;
    private ArrayList<Imagen> imagenesCargadas;

    public Usuario(){
        nombreUsuario = "";
        usuPassword = "";
        correoElectronico = "";
        imagenPerfil = new Imagen();
        imagenesCargadas = new ArrayList<>();
    }

    public Usuario(String nom, String usuAc, String usuPa, String corEle, Imagen imgPe){

        nombreUsuario = nom;
        usuPassword = usuPa;
        correoElectronico = corEle;
        imagenPerfil = imgPe;
        imagenesCargadas = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }



    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Imagen getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(Imagen imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public ArrayList<Imagen> getImagenesCargadas() {
        return imagenesCargadas;
    }

    public void setImagenesCargadas(ArrayList<Imagen> imagenesCargadas) {
        this.imagenesCargadas = imagenesCargadas;
    }

    public void agregarImagenUsuarioCargada(Imagen img){
       imagenesCargadas.add(img);
    }
}
