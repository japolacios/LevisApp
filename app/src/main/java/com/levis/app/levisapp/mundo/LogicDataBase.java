package com.levis.app.levisapp.mundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laboratorioi2t on 15/08/2017.
 */

public class LogicDataBase extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "LevisApp.db";


    public LogicDataBase(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    public void onCreate(SQLiteDatabase db) {
        // Create the database to contain the data for the projects
        db.execSQL(DataBase.SQL_CREATE_TABLE_USUARIOS);
        db.execSQL(DataBase.SQL_CREATE_TABLE_IMAGENES);

    }

    /**
     * This method must be implemented if your application is upgraded and must
     * include the SQL query to upgrade the database from your old to your new
     * schema.
     *
     * @param db         the database being upgraded.
     * @param oldVersion the current version of the database before the upgrade.
     * @param newVersion the version of the database after the upgrade.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Logs that the database is being upgraded
        Log.i(LogicDataBase.class.getSimpleName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion);
    }

    public void insertarUsuario(Usuario user) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(DataBase.DatosColumnas.USUARIO_NOMBRE, user.getNombreUsuario());
            valores.put(DataBase.DatosColumnas.USUARIO_EMAIL, user.getCorreoElectronico());
            valores.put(DataBase.DatosColumnas.USUARIO_CONTRASENA, user.getUsuPassword());
            db.insert(DataBase.TABLA_USUARIOS, null, valores);
            db.close();
        }
    }

    public void cambiarClave(Usuario user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataBase.DatosColumnas.USUARIO_NOMBRE, user.getNombreUsuario());
        valores.put(DataBase.DatosColumnas.USUARIO_EMAIL, user.getCorreoElectronico());
        valores.put(DataBase.DatosColumnas.USUARIO_CONTRASENA, user.getUsuPassword());
        db.update(DataBase.TABLA_USUARIOS, valores, DataBase.DatosColumnas.USUARIO_EMAIL + "=" + user.getCorreoElectronico(), null);
        db.close();
    }

    public void borrarUsuario(Usuario user) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DataBase.TABLA_USUARIOS, DataBase.DatosColumnas.USUARIO_EMAIL + "=" + user.getCorreoElectronico(), null);
        db.close();
    }

    public Usuario buscarUsuario(String identificacion) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.USUARIO_NOMBRE,
                DataBase.DatosColumnas.USUARIO_EMAIL,
                DataBase.DatosColumnas.USUARIO_CONTRASENA
        };

        Cursor c = db.query(DataBase.TABLA_USUARIOS, valores_recuperar,
                DataBase.DatosColumnas.USUARIO_EMAIL + "='" + identificacion+"'",
                null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Usuario user = new Usuario();
                user.setNombreUsuario(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_NOMBRE)));
                user.setCorreoElectronico(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_EMAIL)));
                user.setUsuPassword(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_CONTRASENA)));
                db.close();
                c.close();
                return user;
            }
        }
        db.close();
        c.close();
        return null;
    }

    public void cerrarBD(){
        SQLiteDatabase db = getReadableDatabase();
        db.close();
    }

    public List<Usuario> listaJugadores() {
        SQLiteDatabase db = getReadableDatabase();
        List<Usuario> usuarios = new ArrayList<>();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.USUARIO_NOMBRE,
                DataBase.DatosColumnas.USUARIO_EMAIL,
                DataBase.DatosColumnas.USUARIO_CONTRASENA
        };
        Cursor c = db.query(DataBase.TABLA_USUARIOS, valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            if(c.getCount()>0){
                Usuario user = new Usuario();
                user.setNombreUsuario(c.getString(1));
                user.setCorreoElectronico(c.getString(5));
                user.setUsuPassword(c.getString(7));
                usuarios.add(user);
            }else return null;
        } while (c.moveToNext());
        c.close();
        db.close();
        return usuarios;
    }

    public String getSingleEntry(String userName)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(DataBase.TABLA_USUARIOS, null, " "+DataBase.DatosColumnas.USUARIO_EMAIL+"=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnas.USUARIO_CONTRASENA));
        cursor.close();
        return password;
    }

    public void insertarImagen(Imagen img) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(DataBase.DatosColumnas.IMAGEN_NOMBRE, img.getNombreUsuario());
            valores.put(DataBase.DatosColumnas.IMAGEN_EMAIL, img.getCorreoUsuario());
            valores.put(DataBase.DatosColumnas.IMAGEN_UBICACION, img.getUbicacion());
            valores.put(DataBase.DatosColumnas.IMAGEN_FECHA, img.getFechaCarga());
            valores.put(DataBase.DatosColumnas.IMAGEN_TITULO, img.getTitulo());
            valores.put(DataBase.DatosColumnas.IMAGEN_IMAGEN_MEMORIA, img.getImagenCargada());
            db.insert(DataBase.TABLA_IMAGENES, null, valores);
            db.close();
        }
    }

    public void cambiarTitulo(Imagen img) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataBase.DatosColumnas.IMAGEN_NOMBRE, img.getNombreUsuario());
        valores.put(DataBase.DatosColumnas.IMAGEN_EMAIL, img.getCorreoUsuario());
        valores.put(DataBase.DatosColumnas.IMAGEN_UBICACION, img.getUbicacion());
        valores.put(DataBase.DatosColumnas.IMAGEN_FECHA, img.getFechaCarga());
        valores.put(DataBase.DatosColumnas.IMAGEN_TITULO, img.getTitulo());
        valores.put(DataBase.DatosColumnas.IMAGEN_IMAGEN_MEMORIA, img.getImagenCargada());
        db.update(DataBase.TABLA_IMAGENES, valores, DataBase.DatosColumnas.IMAGEN_NOMBRE + "=" + img.getNombreUsuario(), null);
        db.close();
    }

    public void borrarImagen(Imagen img) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DataBase.TABLA_IMAGENES, DataBase.DatosColumnas.IMAGEN_NOMBRE + "=" + img.getNombreUsuario(), null);
        db.close();
    }

    public List<Imagen> todasLasImagenes() {
        SQLiteDatabase db = getReadableDatabase();
        List<Imagen> imagenes = new ArrayList<>();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.IMAGEN_NOMBRE,
                DataBase.DatosColumnas.IMAGEN_EMAIL,
                DataBase.DatosColumnas.IMAGEN_UBICACION,
                DataBase.DatosColumnas.IMAGEN_FECHA,
                DataBase.DatosColumnas.IMAGEN_TITULO,
                DataBase.DatosColumnas.IMAGEN_IMAGEN_MEMORIA
        };
        Cursor c = db.query(DataBase.TABLA_IMAGENES, valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            if (c.getCount() > 0) {
                Imagen img = new Imagen();

                img.setNombreUsuario(c.getString(1));
                img.setCorreoUsuario(c.getString(2));
                img.setUbicacion(c.getString(3));
                img.setFechaCarga(c.getString(4));
                img.setTitulo(c.getString(5));
                img.setImagenCargada(c.getString(6));
                imagenes.add(img);
            } else return null;
        } while (c.moveToNext());
        c.close();
        db.close();
        return imagenes;
    }

    public List<Imagen> darImagenesUsuario() {
        SQLiteDatabase db = getReadableDatabase();
        List<Imagen> imagenes = new ArrayList<>();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.IMAGEN_NOMBRE,
                DataBase.DatosColumnas.IMAGEN_EMAIL,
                DataBase.DatosColumnas.IMAGEN_UBICACION,
                DataBase.DatosColumnas.IMAGEN_FECHA,
                DataBase.DatosColumnas.IMAGEN_TITULO,
                DataBase.DatosColumnas.IMAGEN_IMAGEN_MEMORIA
        };

        Cursor c = db.query(DataBase.TABLA_IMAGENES, null, " "+DataBase.DatosColumnas.USUARIO_EMAIL+"=?", valores_recuperar, null, null, null, null);
        c.moveToFirst();
        do {
            if(c.getCount()>0){
                Imagen img = new Imagen();
                img.setNombreUsuario(c.getString(1));
                img.setCorreoUsuario(c.getString(2));
                img.setUbicacion(c.getString(3));
                img.setFechaCarga(c.getString(4));
                img.setTitulo(c.getString(5));
                img.setImagenCargada(c.getString(6));
                imagenes.add(img);
            }else return null;
        } while (c.moveToNext());
        c.close();
        db.close();
        return imagenes;
    }

    public Imagen getImagenPerfil(String userName)
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.IMAGEN_NOMBRE,
                DataBase.DatosColumnas.IMAGEN_EMAIL,
                DataBase.DatosColumnas.IMAGEN_UBICACION,
                DataBase.DatosColumnas.IMAGEN_FECHA,
                DataBase.DatosColumnas.IMAGEN_TITULO,
                DataBase.DatosColumnas.IMAGEN_IMAGEN_MEMORIA
        };
        Cursor c = db.query(DataBase.TABLA_IMAGENES, valores_recuperar, " "+DataBase.DatosColumnas.USUARIO_EMAIL+"=? AND "+DataBase.DatosColumnas.USUARIO_NOMBRE+"='imagenPerfil'", null, null, null, null);
        if(c.getCount()<1) // UserName Not Exist
        {
            c.close();
            return null;
        }
        c.moveToFirst();
        Imagen img = new Imagen();
        img.setNombreUsuario(c.getString(1));
        img.setCorreoUsuario(c.getString(2));
        img.setUbicacion(c.getString(3));
        img.setFechaCarga(c.getString(4));
        img.setTitulo(c.getString(5));
        img.setImagenCargada(c.getString(6));
        c.close();
        return img;
    }


}
