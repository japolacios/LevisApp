package com.levis.app.levisapp.mundo;

import android.provider.BaseColumns;

/**
 * Created by juancamilovilladagamboa on 16/09/17.
 */

public class DataBase {
    /**
     * Contains the name of the table to create that contains the row counters.
     */
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_IMAGENES = "imagenes";

    /**
     * Contains the SQL query to use to create the table containing the row counters.
     */
    public static final String SQL_CREATE_TABLE_USUARIOS = "CREATE TABLE "
            + DataBase.TABLA_USUARIOS + " ("
            + DatosColumnas.USUARIO_NOMBRE + " TEXT,"
            + DatosColumnas.USUARIO_EMAIL + " TEXT PRIMARY KEY,"
            + DatosColumnas.USUARIO_CONTRASENA + " TEXT)";

    /**
     * Contains the SQL query to use to create the table containing the row counters.
     */
    public static final String SQL_CREATE_TABLE_IMAGENES = "CREATE TABLE "
            + DataBase.TABLA_IMAGENES + " ("
            + DatosColumnas.IMAGEN_NOMBRE + " TEXT PRIMARY KEY,"
            + DatosColumnas.IMAGEN_EMAIL + " TEXT, "
            + DatosColumnas.IMAGEN_UBICACION + " TEXT,"
            + DatosColumnas.IMAGEN_FECHA + " TEXT,"
            + DatosColumnas.IMAGEN_TITULO + " TEXT,"
            + DatosColumnas.IMAGEN_IMAGEN_MEMORIA + " TEXT,"
            + DatosColumnas.IMAGEN_IMAGEN_TABLA + " BLOB,"
            + "FOREIGN KEY ("+DatosColumnas.IMAGEN_EMAIL+") REFERENCES "+TABLA_USUARIOS+" ("+DatosColumnas.USUARIO_EMAIL+"))";

    /**
     * This class represents the rows for an entry in the row_counter table. The
     * primary key is the _id column from the BaseColumn class.
     */
    public static abstract class DatosColumnas implements BaseColumns {

        public static final String USUARIO_NOMBRE = "nombre";
        public static final String USUARIO_EMAIL = "email";
        public static final String USUARIO_CONTRASENA = "contrasena";

        public static final String IMAGEN_NOMBRE = "nombre";
        public static final String IMAGEN_EMAIL = "email";
        public static final String IMAGEN_UBICACION = "ubicacion";
        public static final String IMAGEN_FECHA = "fecha";
        public static final String IMAGEN_TITULO = "titulo";
        public static final String IMAGEN_IMAGEN_MEMORIA = "imagen_memoria";
        public static final String IMAGEN_IMAGEN_TABLA = "imagen_tabla";


    }
}
