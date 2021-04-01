package es.ucm.fdi.gescom.features.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.features.login.LoginActivity;
import es.ucm.fdi.gescom.features.register.RegisterActivity;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        getIntent();
    }


    public void launchLogin(View view) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //aquí habria que devolver lo que devuelva el login
        }

    public void launchRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        //aquí habria que devolver lo que devuelva el login
    }
}