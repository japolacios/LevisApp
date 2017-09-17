package com.levis.app.levisapp.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by juancamilovilladagamboa on 16/09/17.
 */

public class Usuario implements Serializable {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String idUsuario;

    private String tipoID;
    private String correoElectronico;
    private String usuarioAcceso;
    private String usuPassword;
    private Imagen imagenPerfil;
    private ArrayList<Imagen> imagenesCargadas;

    public Usuario(){
        nombreUsuario = "";
        usuarioAcceso = "";
        usuPassword = "";
        correoElectronico = "";
        imagenPerfil = new Imagen();
        imagenesCargadas = new ArrayList<>();
    }

    public Usuario(String nom, String usuAc, String usuPa, String corEle, Imagen imgPe){

        nombreUsuario = nom;
        usuarioAcceso = usuAc;
        usuPassword = usuPa;
        correoElectronico = corEle;
        imagenPerfil = imgPe;
        imagenesCargadas = new ArrayList<>();
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuarioAcceso() {
        return usuarioAcceso;
    }

    public void setUsuarioAcceso(String usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
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

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
