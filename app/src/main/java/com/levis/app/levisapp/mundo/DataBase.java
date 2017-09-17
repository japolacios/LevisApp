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

    /**
     * Contains the SQL query to use to create the table containing the row counters.
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE "
            + DataBase.TABLA_USUARIOS + " ("
            + DatosColumnas.USUARIO_NOMBRE + " TEXT,"
            + DatosColumnas.USUARIO_APELLIDO + " TEXT,"
            + DatosColumnas.USUARIO_IDENTIFICACION + " TEXT,"
            + DatosColumnas.USUARIO_TIPO_IDENTIFICACION + " TEXT,"
            + DatosColumnas.USUARIO_EMAIL + " TEXT,"
            + DatosColumnas.USUARIO_USERNAME + " TEXT PRIMARY KEY,"
            + DatosColumnas.USUARIO_CONTRASENA + " TEXT)";


    /**
     * This class represents the rows for an entry in the row_counter table. The
     * primary key is the _id column from the BaseColumn class.
     */
    public static abstract class DatosColumnas implements BaseColumns {

        public static final String USUARIO_NOMBRE = "nombre";
        public static final String USUARIO_APELLIDO = "apellido";
        public static final String USUARIO_IDENTIFICACION = "identificacion";
        public static final String USUARIO_TIPO_IDENTIFICACION = "tipo_identificacion";
        public static final String USUARIO_EMAIL = "email";
        public static final String USUARIO_USERNAME="nombre de usuario";
        public static final String USUARIO_CONTRASENA = "contrasena";

    }
}
