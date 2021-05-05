package es.ucm.fdi.gescom.features.avisos;

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
import es.ucm.fdi.gescom.datacache.Aviso;
import es.ucm.fdi.gescom.features.avisos.add_aviso.AddAvisoActivity;
import es.ucm.fdi.gescom.features.avisos.editar_aviso.EditarAvisoActivity;

public class AvisosActivity extends BaseActivity implements AvisosView {
    private RecyclerView mRecyclerAvisos;
    private FloatingActionButton mNuevoAviso;
    private AvisosPresenter mPresenter;
    private ArrayList<Aviso> mAvisosList;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);

        getIntent();
        bindViews();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Avisos");

        mPresenter = new AvisosPresenter(this);

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
    }

    @Override
    public void launchAddAviso() {
        Intent intent = new Intent(this, AddAvisoActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchEditarAviso() {
        Intent intent = new Intent(this, EditarAvisoActivity.class);
        startActivity(intent);
    }

    @Override
    public void bindViews() {
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerAvisos = findViewById(R.id.recycler_avisos);
        mNuevoAviso = findViewById(R.id.avisos_admin_fab);
    }
}