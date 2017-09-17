package com.levis.app.levisapp.mundo;

import android.provider.BaseColumns;

/**
 * Created by Laboratorioi2t on 15/08/2017.
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
            + DatosColumnas.JUGADOR_NOMBRE + " TEXT,"
            + DatosColumnas.JUGADOR_APELLIDO + " TEXT,"
            + DatosColumnas.JUGADOR_IDENTIFICACION + " TEXT PRIMARY KEY,"
            + DatosColumnas.JUGADOR_TIPO_IDENTIFICACION + " TEXT,"
            + DatosColumnas.JUGADOR_EMAIL + " TEXT,"
            + DatosColumnas.JUGADOR_CONTRASENA + " TEXT,"
            + DatosColumnas.JUGADOR_PARTIDAS_JUGADAS + " INT,"
            + DatosColumnas.JUGADOR_PARTIDAS_GANADAS + " INT,"
            + DatosColumnas.JUGADOR_RESPUESTAS_CORRECTAS + " INT,"
            + DatosColumnas.JUGADOR_RESPUESTAS_INCORRECTAS + " INT)";


    /**
     * This class represents the rows for an entry in the row_counter table. The
     * primary key is the _id column from the BaseColumn class.
     */
    public static abstract class DatosColumnas implements BaseColumns {

        public static final String JUGADOR_NOMBRE = "nombre";
        public static final String JUGADOR_APELLIDO = "apellido";
        public static final String JUGADOR_IDENTIFICACION = "identificacion";
        public static final String JUGADOR_TIPO_IDENTIFICACION = "tipo_identificacion";
        public static final String JUGADOR_EMAIL = "email";
        public static final String JUGADOR_CONTRASENA = "contrasena";
        public static final String JUGADOR_PARTIDAS_JUGADAS = "partidas_jugadas";
        public static final String JUGADOR_PARTIDAS_GANADAS = "partidas_ganadas";
        public static final String JUGADOR_RESPUESTAS_CORRECTAS = "correctas";
        public static final String JUGADOR_RESPUESTAS_INCORRECTAS = "incorrectas";

    }
}
