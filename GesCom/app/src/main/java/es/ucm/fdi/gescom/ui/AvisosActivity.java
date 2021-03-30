package es.ucm.fdi.gescom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.ucm.fdi.gescom.R;

public class AvisosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}