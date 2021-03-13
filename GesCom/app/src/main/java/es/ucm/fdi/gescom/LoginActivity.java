package es.ucm.fdi.gescom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void comprobarLogin(View view) {
        //aqui habria que comprobar que el usuario existe
        //si existe se tendría que iniacializar el usuario.clase
        //si no existe debería sacar un Toast avisando de que está mal


        //caso de que está mal
        Toast toast = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
        toast.show();

    }
}