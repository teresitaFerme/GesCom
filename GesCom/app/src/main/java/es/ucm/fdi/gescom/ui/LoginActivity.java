package es.ucm.fdi.gescom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.roomdatabase.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIntent();
        mUsername = findViewById(R.id.editText_user);
        mPassword = findViewById(R.id.editText_password);
    }

    public void comprobarLogin(View view) {
        //aqui habria que comprobar que el usuario existe
        //si existe se tendría que iniacializar el usuario.clase
        //si no existe debería sacar un Toast avisando de que está mal
        //caso correcto
        String username = String.valueOf(mUsername.getText());
        String password = String.valueOf(mPassword.getText());
        //TODO comprobar que el usuario es válido

        Usuario user = new Usuario(username, password);

        Intent intent = new Intent(this, PantallaPrincipal.class);
        startActivity(intent);

        //caso de que está mal
        //Toast toast = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
       // toast.show();

    }

    public void resetPassword(View view) {
        //TODO resetPassword activity -> enviar un correo para resetear la contraseña
    }
}