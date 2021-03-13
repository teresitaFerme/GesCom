package es.ucm.fdi.gescom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginRegister extends AppCompatActivity {

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