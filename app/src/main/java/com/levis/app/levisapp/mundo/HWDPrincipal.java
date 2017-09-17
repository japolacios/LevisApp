package com.levis.app.levisapp.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by juancamilovilladagamboa on 16/09/17.
 */

public class HWDPrincipal implements Serializable {

    private Usuario usuarioActual;
    private ArrayList<Imagen> imagenesUsuarios;

   public HWDPrincipal(){
       usuarioActual = new Usuario();
       imagenesUsuarios = new ArrayList<>();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public ArrayList<Imagen> getImagenesUsuarios() {
        return imagenesUsuarios;
    }

    public void setImagenesUsuarios(ArrayList<Imagen> imagenesUsuarios) {
        this.imagenesUsuarios = imagenesUsuarios;
    }

    public void iniciarSesionUsuario(String usuAcc, String pass){

    }

    public void cerrarSesionUsuario(){

    }

    public void cargarFeedsUsuario(){

    }

    public void cargarImagenUsuario(){

    }

    public void cargarImagenPerfilUsuario(){

    }

}
