package es.ucm.fdi.gescom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchView();
    }

    private void launchView(){
        //si no tiene la sesion iniciada entonces puede o iniciar sesion o registrarse
        Intent intent = new Intent(this, LoginRegister.class);
        startActivity(intent);

    }


}