package es.ucm.fdi.gescom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchLogin();
    }

    private void launchLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //aquí habria que devolver lo que devuelva el login
    }
}