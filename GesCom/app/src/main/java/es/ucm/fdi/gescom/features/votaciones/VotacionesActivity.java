package es.ucm.fdi.gescom.features.votaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;

public class VotacionesActivity extends BaseActivity implements VotacionesView{
    private VotacionesPresenter mPresenter;
    private FloatingActionButton mFab;
    private RecyclerView mVotacionesPendientes, mVotacionesAnteriores;
    private Button mEnviarVoto;
    private ImageButton mFavor, mContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);

        mPresenter = new VotacionesPresenter(this);

        mFab = findViewById(R.id.votaciones_admin_fab);

        //TODO ESTO DEBERÍA IR EN EL VIEWHOLDER
        mVotacionesPendientes = findViewById(R.id.recycler_votaciones_pendientes);
        mVotacionesAnteriores = findViewById(R.id.recycler_votaciones_anteriores);
        mEnviarVoto = findViewById(R.id.votacion_button);
        mFavor = findViewById(R.id.votacion_button_favor);
        mContra = findViewById(R.id.votacion_button_contra);
    }
}