package es.ucm.fdi.gescom.features.avisos.editar_aviso;

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
import es.ucm.fdi.gescom.features.avisos.AvisosActivity;

public class EditarAvisoActivity extends BaseActivity implements EditarAvisoView {
    private EditarAvisoPresenter mEditarAvisoPresenter;
    private EditText mAsunto, mDescripcion;
    private Toolbar mToolbar;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aviso);

        getIntent();

        mEditarAvisoPresenter = new EditarAvisoPresenter(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Editar Aviso");

        //TODO L- En asunto y la descripción tendría que cargar lo que ya está en la BBDD, si no cuando le des a editar saldría todo en blanco (lo mismo que crear uno nuevo) en vez de dejarte editarlo
        mAsunto = findViewById(R.id.editar_aviso_asunto);
        mDescripcion = findViewById(R.id.editar_aviso_descripcion);

        mButton = findViewById(R.id.button_editar_aviso);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditarAvisoPresenter.validateIncidence(String.valueOf(mAsunto.getText()), String.valueOf(mDescripcion.getText()));
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
        Intent intent = new Intent(this, AvisosActivity.class);
        startActivity(intent);
    }
}