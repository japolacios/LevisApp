package com.levis.app.levisapp.mundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


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
            valores.put(DataBase.DatosColumnas.USUARIO_NOMBRE, user.getNombreUsuario());
            valores.put(DataBase.DatosColumnas.USUARIO_IDENTIFICACION, user.getIdUsuario());
            valores.put(DataBase.DatosColumnas.USUARIO_EMAIL, user.getCorreoElectronico());
           // valores.put(DataBase.DatosColumnas.USUARIO_PIC, user.getImagenPerfil());
            valores.put(DataBase.DatosColumnas.USUARIO_CONTRASENA, user.getUsuPassword());
            db.insert(DataBase.TABLA_USUARIOS, null, valores);
            db.close();
        }
    }

    public void cambiarClave(Usuario user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DataBase.DatosColumnas.USUARIO_NOMBRE, user.getNombreUsuario());
        valores.put(DataBase.DatosColumnas.USUARIO_APELLIDO, user.getApellidoUsuario());
        valores.put(DataBase.DatosColumnas.USUARIO_IDENTIFICACION, user.getIdUsuario());
        valores.put(DataBase.DatosColumnas.USUARIO_TIPO_IDENTIFICACION, user.getTipoID());
        valores.put(DataBase.DatosColumnas.USUARIO_EMAIL, user.getCorreoElectronico());
        valores.put(DataBase.DatosColumnas.USUARIO_USERNAME, user.getUsuarioAcceso());
        valores.put(DataBase.DatosColumnas.USUARIO_CONTRASENA, user.getUsuPassword());
        db.update(DataBase.TABLA_USUARIOS, valores, DataBase.DatosColumnas.USUARIO_USERNAME + "=" + user.getUsuarioAcceso(), null);
        db.close();
    }

    public void borrarUsuario(Usuario user) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DataBase.TABLA_USUARIOS, DataBase.DatosColumnas.USUARIO_USERNAME + "=" + user.getUsuarioAcceso(), null);
        db.close();
    }

    public Usuario buscarUsuario(String identificacion) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.USUARIO_NOMBRE,
                DataBase.DatosColumnas.USUARIO_APELLIDO,
                DataBase.DatosColumnas.USUARIO_IDENTIFICACION,
                DataBase.DatosColumnas.USUARIO_TIPO_IDENTIFICACION,
                DataBase.DatosColumnas.USUARIO_EMAIL,
                DataBase.DatosColumnas.USUARIO_USERNAME,
                DataBase.DatosColumnas.USUARIO_CONTRASENA
        };

        Cursor c = db.query(DataBase.TABLA_USUARIOS, valores_recuperar,
                DataBase.DatosColumnas.USUARIO_USERNAME + "='" + identificacion+"'",
                null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                Usuario user = new Usuario();
                user.setNombreUsuario(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_NOMBRE)));
                user.setApellidoUsuario(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_APELLIDO)));
                user.setIdUsuario(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_IDENTIFICACION)));
                user.setTipoID(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_TIPO_IDENTIFICACION)));
                user.setCorreoElectronico(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_EMAIL)));
                user.setUsuarioAcceso(c.getString(c.getColumnIndex(DataBase.DatosColumnas.USUARIO_USERNAME)));
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

    public List<Usuario> listaUsuarios() {
        SQLiteDatabase db = getReadableDatabase();
        List<Usuario> usuarios = new ArrayList<>();
        String[] valores_recuperar = {
                DataBase.DatosColumnas.USUARIO_NOMBRE,
                DataBase.DatosColumnas.USUARIO_APELLIDO,
                DataBase.DatosColumnas.USUARIO_IDENTIFICACION,
                DataBase.DatosColumnas.USUARIO_TIPO_IDENTIFICACION,
                DataBase.DatosColumnas.USUARIO_EMAIL,
                DataBase.DatosColumnas.USUARIO_USERNAME,
                DataBase.DatosColumnas.USUARIO_CONTRASENA
        };
        Cursor c = db.query(DataBase.TABLA_USUARIOS, valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            if(c.getCount()>0){
                Usuario user = new Usuario();
                user.setNombreUsuario(c.getString(1));
                user.setApellidoUsuario(c.getString(2));
                user.setIdUsuario(c.getString(3));
                user.setTipoID(c.getString(4));
                user.setCorreoElectronico(c.getString(5));
                user.setUsuarioAcceso(c.getString(6));
                user.setUsuPassword(c.getString(7));
                usuarios.add(user);
            }else return null;
        } while (c.moveToNext());
        c.close();
        db.close();
        return usuarios;
    }


}
