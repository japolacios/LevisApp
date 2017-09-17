package com.levis.app.levisapp.mundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.tics.mathchallenge.modelo.Jugador;

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
        db.execSQL(DataBase.SQL_CREATE_TABLE);

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
            valores.put(DataBase.DatosColumnas.JUGADOR_NOMBRE, user.getNombre());
            valores.put(DataBase.DatosColumnas.JUGADOR_APELLIDO, player.getApellido());
            valores.put(DataBase.DatosColumnas.JUGADOR_IDENTIFICACION, player.getIdentificacion());
            valores.put(DataBase.DatosColumnas.JUGADOR_TIPO_IDENTIFICACION, player.getTipoIdentificacion());
            valores.put(DataBase.DatosColumnas.JUGADOR_EMAIL, player.getCorreo());
            valores.put(DataBase.DatosColumnas.JUGADOR_CONTRASENA, player.getContrasena());
            valores.put(DataBase.DatosColumnas.JUGADOR_PARTIDAS_JUGADAS, player.getPartidasJugadas());
            valores.put(DataBase.DatosColumnas.JUGADOR_PARTIDAS_GANADAS, player.getPartidasGanadas());
            valores.put(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS, player.getBuenas());
            valores.put(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS, player.getMalas());
            db.insert(DataBase.TABLA_JUGADORES, null, valores);
            db.close();
        }
    }

    public void modificarJugador(Jugador player) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataBase.DatosColumnas.JUGADOR_NOMBRE, player.getNombre());
        valores.put(DataBase.DatosColumnas.JUGADOR_APELLIDO, player.getApellido());
        valores.put(DataBase.DatosColumnas.JUGADOR_IDENTIFICACION, player.getIdentificacion());
        valores.put(DataBase.DatosColumnas.JUGADOR_TIPO_IDENTIFICACION, player.getTipoIdentificacion());
        valores.put(DataBase.DatosColumnas.JUGADOR_EMAIL, player.getCorreo());
        valores.put(DataBase.DatosColumnas.JUGADOR_CONTRASENA, player.getContrasena());
        valores.put(DataBase.DatosColumnas.JUGADOR_PARTIDAS_JUGADAS, player.getPartidasJugadas());
        valores.put(DataBase.DatosColumnas.JUGADOR_PARTIDAS_GANADAS, player.getPartidasGanadas());
        valores.put(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS, player.getBuenas());
        valores.put(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS, player.getMalas());
        db.update(DataBase.TABLA_JUGADORES, valores, DataBase.DatosColumnas.JUGADOR_IDENTIFICACION + "=" + player.getIdentificacion(), null);
        db.close();
    }

    public void borrarJugador(Jugador player) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DataBase.TABLA_JUGADORES, DataBase.DatosColumnas.JUGADOR_IDENTIFICACION + "=" + player.getIdentificacion(), null);
        db.close();
    }

    public Jugador buscarJugador(String identificacion) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.JUGADOR_NOMBRE,
                DataBase.DatosColumnas.JUGADOR_APELLIDO,
                DataBase.DatosColumnas.JUGADOR_IDENTIFICACION,
                DataBase.DatosColumnas.JUGADOR_TIPO_IDENTIFICACION,
                DataBase.DatosColumnas.JUGADOR_EMAIL,
                DataBase.DatosColumnas.JUGADOR_CONTRASENA,
                DataBase.DatosColumnas.JUGADOR_PARTIDAS_JUGADAS,
                DataBase.DatosColumnas.JUGADOR_PARTIDAS_GANADAS,
                DataBase.DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS,
                DataBase.DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS
        };

        Cursor c = db.query(DataBase.TABLA_JUGADORES, valores_recuperar,
                DataBase.DatosColumnas.JUGADOR_IDENTIFICACION + "='" + identificacion+"'",
                null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Jugador jugador = new Jugador(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_NOMBRE)));
                jugador.setApellido(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_APELLIDO)));
                jugador.setIdentificacion(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_IDENTIFICACION)));
                jugador.setTipoIdentificacion(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_TIPO_IDENTIFICACION)));
                jugador.setCorreo(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_EMAIL)));
                jugador.setContrasena(c.getString(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_CONTRASENA)));
                jugador.setPartidasGanadas(c.getInt(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_PARTIDAS_GANADAS)));
                jugador.setPartidasJugadas(c.getInt(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_PARTIDAS_JUGADAS)));
                jugador.setBuenas(c.getInt(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS)));
                jugador.setMalas(c.getInt(c.getColumnIndex(DataBase.DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS)));
                db.close();
                c.close();
                return jugador;
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

    public List<Jugador> listaJugadores() {
        SQLiteDatabase db = getReadableDatabase();
        List<Jugador> jugadores = new ArrayList<>();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.JUGADOR_NOMBRE,
                DataBase.DatosColumnas.JUGADOR_APELLIDO,
                DataBase.DatosColumnas.JUGADOR_IDENTIFICACION,
                DataBase.DatosColumnas.JUGADOR_TIPO_IDENTIFICACION,
                DataBase.DatosColumnas.JUGADOR_EMAIL,
                DataBase.DatosColumnas.JUGADOR_CONTRASENA,
                DataBase.DatosColumnas.JUGADOR_PARTIDAS_JUGADAS,
                DataBase.DatosColumnas.JUGADOR_PARTIDAS_GANADAS,
                DataBase.DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS,
                DataBase.DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS
        };
        Cursor c = db.query(DataBase.TABLA_JUGADORES, valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            if(c.getCount()>0){
                Jugador jugador = new Jugador(c.getString(0));
                jugador.setApellido(c.getString(1));
                jugador.setIdentificacion(c.getString(2));
                jugador.setTipoIdentificacion(c.getString(3));
                jugador.setCorreo(c.getString(4));
                jugador.setContrasena(c.getString(5));
                jugador.setPartidasGanadas(c.getInt(6));//Integer.valueOf(
                jugador.setPartidasJugadas(c.getInt(7));
                jugador.setBuenas(c.getInt(8));
                jugador.setMalas(c.getInt(9));
                jugadores.add(jugador);
            }else return null;
        } while (c.moveToNext());
        c.close();
        db.close();
        return jugadores;
    }


}
