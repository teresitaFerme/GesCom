package es.ucm.fdi.gescom.features.incidencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.Incidencia;
import es.ucm.fdi.gescom.features.incidencias.reportar_incidencia.ReportarIncidenciaActivity;

public class IncidenciasActivity extends BaseActivity implements IncidenciasView {
    private IncidenciasPresenter mPresenter;
    private RecyclerView mIncidencias;
    private Button mButton;
    private ArrayList<Incidencia> mIncidenciasList;
    private Spinner mFilterSpinner;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias_admin);

        bindViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Incidencias");

        mPresenter = new IncidenciasPresenter(this);

        mIncidenciasList = mPresenter.getIncidencias();
        if (mIncidenciasList.size() != 0) {
            IncidenciasAdapterActivity incidencesAdapter = new IncidenciasAdapterActivity(this, mIncidenciasList, this);
            mIncidencias.setAdapter(incidencesAdapter);
            mIncidencias.setLayoutManager(new LinearLayoutManager(this));
        }else findViewById(R.id.incidencias_no_incidencias).setVisibility(View.VISIBLE);

        if (mPresenter.checkAdmin()) {
            mButton.setVisibility(View.GONE);
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.launchNewIncidence();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.valores_filtros_incidencias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFilterSpinner.setAdapter(adapter);
    }

    @Override
    public void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        mIncidencias = findViewById(R.id.incidencias_recycler_view_pendientes);
        mButton = findViewById(R.id.button_incidencias);
        mFilterSpinner = findViewById(R.id.incidencias_filter_spinner);
    }

    @Override
    public void launchNewIncidence() {
        Intent intent = new Intent(this, ReportarIncidenciaActivity.class);
        startActivity(intent);
    }
}