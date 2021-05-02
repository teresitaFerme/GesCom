package es.ucm.fdi.gescom.features.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;

public class MainActivity extends AppCompatActivity {
    private GesComApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        mApp = GesComApp.getApp();

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3*1000);

                    Intent i=new Intent(getBaseContext(),LoginRegisterActivity.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();

        //NO COMMITEAR ESTE CAMBIO
        //this.deleteDatabase("GesCom.db");

    }
}