package es.ucm.fdi.gescom.features.votaciones.add_votacion;

import androidx.appcompat.app.AppCompatActivity;
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
import es.ucm.fdi.gescom.features.votaciones.VotacionesActivity;

public class AddVotacionActivity extends BaseActivity implements AddVotacionView{
    private AddVotacionPresenter mPresenter;
    private EditText mTitle, mDescription;
    private Button mAddVotacionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_votacion);

        getIntent();

        mPresenter = new AddVotacionPresenter(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Añadir votación");

        mTitle = findViewById(R.id.votacion_editText_title);
        mDescription = findViewById(R.id.votacion_editText_description);
        mAddVotacionButton = findViewById(R.id.votacion_add_button);

        mAddVotacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addVotacion(String.valueOf(mTitle.getText()), String.valueOf(mDescription.getText()));
            }
        });

    }

    @Override
    public void addSuccessful() {
        Intent intent = new Intent(this, VotacionesActivity.class);
        startActivity(intent);
    }
}