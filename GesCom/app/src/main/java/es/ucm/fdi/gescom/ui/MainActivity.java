package es.ucm.fdi.gescom.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.ViewModel;
import es.ucm.fdi.gescom.roomdatabase.Comunidad;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ViewModel mComunidadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: poner que salga el splash unos segundos antes de que aparezca la primera vista
//        SystemClock.sleep(100000);

        mComunidadViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        mComunidadViewModel.getTodasComunidades().observe(this, new Observer<List<Comunidad>>() {
            @Override
            public void onChanged(@Nullable final List<Comunidad> words) {
                // Update the cached copy of the words in the adapter.

            }
        });
        launchView();
    }

    private void launchView(){
        //si no tiene la sesion iniciada entonces puede o iniciar sesion o registrarse
        Intent intent = new Intent(this, LoginRegister.class);
        startActivity(intent);
    }


}