package es.ucm.fdi.gescom.features.votaciones;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.datacache.Votacion;
import es.ucm.fdi.gescom.features.principal.IncidencesAdapter;

public class VotacionesActivity extends BaseActivity implements VotacionesView{
    private VotacionesPresenter mPresenter;
    private FloatingActionButton mFab;
    private RecyclerView mVotacionesPendientes, mVotacionesAnteriores;
    private ArrayList<Votacion> mVotacionesPendientesList = new ArrayList<>();
    private ArrayList<Votacion> mVotacionesAnterioresList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);
        getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Votaciones");

        mPresenter = new VotacionesPresenter(this);

        mFab = findViewById(R.id.votaciones_admin_fab);
        if(mPresenter.checkAdmin()){
            mFab.setVisibility(View.VISIBLE);
        }
        //TODO hacer que si el admin le da aquí pueda añadir una nueva votacion
        mVotacionesPendientes = findViewById(R.id.recycler_votaciones_pendientes);
        mVotacionesAnteriores = findViewById(R.id.recycler_votaciones_anteriores);


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
}