package es.ucm.fdi.gescom.features.avisos;

import android.app.AlertDialog;
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
        populateRecyclerView();


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

    }



    @Override
    public void launchAddAviso() {
        Intent intent = new Intent(this, AddAvisoActivity.class);
        startActivity(intent);
    }

    @Override
    public void deleteAviso(int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Estás seguro de que quieres eliminar este aviso?");
        alertDialog.setPositiveButton("Sí", (dialog, which) -> {
            mPresenter.validateDeleteAviso(id);
            populateRecyclerView();
        });
        alertDialog.setNegativeButton("No", (dialog, which) -> {
            //Do nothing
        });
        alertDialog.show();
    }

    @Override
    public void editAviso(int position) {
            Intent intent = new Intent(this, EditAvisoActivity.class);
            intent.putExtra("user", mAvisosList.get(position).getId());
            startActivity(intent);

    }

    @Override
    public void modifyAviso(int position, boolean delete, boolean edit) {
        if(edit){
            editAviso(position);
        }else if(delete){
            deleteAviso(mAvisosList.get(position).getId());
            populateRecyclerView();
        }
    }

    @Override
    public void populateRecyclerView() {
        mAvisosList = mPresenter.getAvisos();
            AvisosAdapter avisosAdapter = new AvisosAdapter(this, mAvisosList, this);
            mRecyclerAvisos.setAdapter(avisosAdapter);
            mRecyclerAvisos.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void bindViews() {

    }
}