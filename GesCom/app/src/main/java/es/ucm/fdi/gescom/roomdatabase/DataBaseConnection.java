package es.ucm.fdi.gescom.roomdatabase;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.ucm.fdi.gescom.ui.PantallaPrincipal;

public class DataBaseConnection extends AsyncTask<Void, Void, Void> {
    private String edServidor = "sql11.freemysqlhosting.net", edPuerto = "3306", edUsuario = "sql11401998", edPassword = "Y5hMbgyCqi";
    private String baseDatos = "sql11401998";

    @Override
    protected Void doInBackground(Void... voids) {

        /*Asignamos el driver a una variable de tipo String.*/
        String driver = "com.mysql.jdbc.Driver";

        /*Cargamos el driver del conector JDBC.*/
        try {
            Class.forName(driver).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {//EL ERROR DA AQUI
            e.printStackTrace();
        }
        /*Variable boolean que almacenará si el estado de la conexión es true o false.*/
        boolean estadoConexion = false;
        //Inicializamos la Clase Connection encarada de conectar con la base de datos.
        Connection conexionMySQL = null;

        /*Construímos la url para establecer la conexión.*/
        String urlMySQL = "jdbc:mysql://" + edServidor + ":" + edPuerto + "/";

    /*Establecemos la conexión con el Servidor MySQL indicándole como parámetros la url construida,
    la Base de Datos a la que vamos a conectarnos, y el usuario y contraseña de acceso al servidor.*/
        try {
            conexionMySQL = DriverManager.getConnection(urlMySQL + baseDatos, edUsuario, edPassword);
            //TODO ver porqué da error
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
;
        /*Comprobamos que la conexión se ha establecido.*/
        try {
            assert conexionMySQL != null;
            if (!conexionMySQL.isClosed()) {
                estadoConexion = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Log.i("ESTADO CONEXION", String.valueOf(estadoConexion));
        return null;
    }
}
