package es.ucm.fdi.gescom.features.reportar_incidencia;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.principal.PrincipalActivity;

public class ReportarIncidenciaActivity extends BaseActivity implements ReportarIncidenciaView {
    private ReportarIncidenciaPresenter mIncidenciasPresenter;
    private EditText mAsunto, mDescripcion;
    private Toolbar mToolbar;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_incidencia);

        mIncidenciasPresenter = new ReportarIncidenciaPresenter(this);

        mToolbar = findViewById(R.id.toolbar);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Reportar incidencia");

        mAsunto = findViewById(R.id.incidencias_asunto);
        mDescripcion = findViewById(R.id.incidencias_descripcion);

        mButton = findViewById(R.id.button_incidencias);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncidenciasPresenter.validateIncidence(String.valueOf(mAsunto.getText()), String.valueOf(mDescripcion.getText()));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void reportIncomplete() {
        Toast toast = Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void reportServerFailure() {
        Toast toast = Toast.makeText(this, "No se ha podido reportar por error en el servidor. Intentelo más tarde", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void reportSuccessful() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

}