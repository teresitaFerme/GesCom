package es.ucm.fdi.gescom.features.avisos;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.features.incidencias.IncidenciasAdapterActivity;

import static androidx.recyclerview.widget.RecyclerView.*;

public class AvisosActivity extends BaseActivity implements AvisosView {
    private RecyclerView mRecyclerAvisos;
    private FloatingActionButton mNuevoAviso;
    private AvisosPresenter mPresenter;
    private ArrayList<Aviso> mAvisosList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);

        getIntent();

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Avisos");

        mPresenter = new AvisosPresenter(this);

        mRecyclerAvisos = findViewById(R.id.recycler_avisos);


        mNuevoAviso = findViewById(R.id.avisos_admin_fab);
        if(mPresenter.checkAdmin()){
            mNuevoAviso.setVisibility(View.VISIBLE);
        }
        mNuevoAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addAviso();
            }
        });

        mAvisosList = mPresenter.getAvisos();
        if(mAvisosList.size() != 0){
            AvisosAdapter avisosAdapter = new AvisosAdapter(this, mAvisosList);
            mRecyclerAvisos.setAdapter(avisosAdapter);

            mRecyclerAvisos.setLayoutManager(new LinearLayoutManager(this));
        }
        //TODO que los avisos puedan filtrarse por dias, semanas o meses
        //TODO poner bien el recycler view y que salgan todos los card views
    }

    @Override
    public void launchAddAviso() {
        //TODO crear pantalla de add aviso SOLO para el admin
    }
}