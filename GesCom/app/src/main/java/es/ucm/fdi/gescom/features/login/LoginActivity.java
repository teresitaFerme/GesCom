package es.ucm.fdi.gescom.features.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.roomdatabase.Usuario;
import es.ucm.fdi.gescom.features.principal.PrincipalActivity;

public class LoginActivity extends BaseActivity implements LoginView{
    private LoginPresenter mLoginPresenter;
    private EditText mUsername, mPassword;
    private Button mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIntent();

        mLoginPresenter = new LoginPresenter(this);

        mUsername = findViewById(R.id.editText_user);
        mPassword = findViewById(R.id.editText_password);

        mLogIn = findViewById(R.id.button_login);
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.validateLogin(String.valueOf(mUsername.getText()), String.valueOf(mPassword.getText()));
            }
        });
        //TODO pensar que pasa con el reset password
    }

    public void resetPassword(View view) {
        //TODO resetPassword activity -> enviar un correo para resetear la contraseña
    }

    @Override
    public void loginSuccessful() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast toast = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
        toast.show();
        mUsername.setText("");
        mPassword.setText("");
    }

    @Override
    public void fillingFailure() {
        Toast toast = Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_LONG);
        toast.show();
    }
}