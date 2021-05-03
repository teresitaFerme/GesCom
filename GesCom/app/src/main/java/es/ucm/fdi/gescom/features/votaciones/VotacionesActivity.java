package es.ucm.fdi.gescom.features.votaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.features.votaciones.add_votacion.AddVotacionActivity;

public class VotacionesActivity extends BaseActivity implements VotacionesView{
    private VotacionesPresenter mPresenter;
    private FloatingActionButton mFab;
    private RecyclerView mVotacionesPendientes, mVotacionesAnteriores;
    private ArrayList<Votacion> mVotacionesPendientesList = new ArrayList<>();
    private ArrayList<Votacion> mVotacionesAnterioresList = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);
        getIntent();
        bindViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Votaciones");

        mPresenter = new VotacionesPresenter(this);

        if(mPresenter.checkAdmin()){
            mFab.setVisibility(View.VISIBLE);
        }
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addVotacion();
            }
        });


        mVotacionesPendientesList = mPresenter.getVotacionesPendientes();
        if(mVotacionesPendientesList.size() != 0){
            VotacionesAdapter votacionesAdapter = new VotacionesAdapter(this, mVotacionesPendientesList);
            mVotacionesPendientes.setAdapter(votacionesAdapter);

            mVotacionesPendientes.setLayoutManager(new LinearLayoutManager(this));
        }

        mVotacionesAnterioresList = mPresenter.getVotacionesAnteriores();
        if(mVotacionesAnterioresList.size() != 0){
            VotacionesAdapter votacionesAdapter = new VotacionesAdapter(this, mVotacionesAnterioresList);
            mVotacionesAnteriores.setAdapter(votacionesAdapter);

            mVotacionesAnteriores.setLayoutManager(new LinearLayoutManager(this));
        }

        //TODO el admin debe poder cerrar las votaciones
    }

    @Override
    public void launchAddVotacion() {
        Intent intent = new Intent(this, AddVotacionActivity.class);
        startActivity(intent);
    }


    @Override
    public void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        mFab = findViewById(R.id.votaciones_admin_fab);
        mVotacionesPendientes = findViewById(R.id.recycler_votaciones_pendientes);
        mVotacionesAnteriores = findViewById(R.id.recycler_votaciones_anteriores);
    }
}