package es.ucm.fdi.gescom.features.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;
import es.ucm.fdi.gescom.datacache.GesComApp;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private GesComApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: poner que salga el splash unos segundos antes de que aparezca la primera vista
//        SystemClock.sleep(100000);
        mApp = GesComApp.getApp();
        launchView();

        //NO COMMITEAR ESTE CAMBIO
        //this.deleteDatabase("GesCom.db");

    }

    private void launchView(){
        //si no tiene la sesion iniciada entonces puede o iniciar sesion o registrarse
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        startActivity(intent);
    }


}