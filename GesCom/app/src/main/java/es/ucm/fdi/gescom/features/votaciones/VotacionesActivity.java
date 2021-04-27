package es.ucm.fdi.gescom.features.votaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;

public class VotacionesActivity extends BaseActivity implements VotacionesView{
    private VotacionesPresenter mPresenter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);

        mPresenter = new VotacionesPresenter(this);
        mFab = findViewById(R.id.votaciones_admin_fab);
    }
}