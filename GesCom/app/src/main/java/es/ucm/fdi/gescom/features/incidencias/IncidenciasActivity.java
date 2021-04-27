package es.ucm.fdi.gescom.features.incidencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.features.principal.IncidencesAdapter;

public class IncidenciasActivity extends BaseActivity implements IncidenciasView {
    private IncidenciasPresenter mPresenter;
    private RecyclerView mIncidencias;
    private ArrayList<Incidencia> mIncidenciasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias_admin);

        mPresenter = new IncidenciasPresenter(this);
        mIncidencias = findViewById(R.id.incidencias_recycler_view);

        mIncidenciasList = mPresenter.getIncidencias();
        if(mIncidenciasList.size() != 0){
            IncidencesAdapter incidencesAdapter = new IncidencesAdapter(this, mIncidenciasList);
            mIncidencias.setAdapter(incidencesAdapter);

            mIncidencias.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}