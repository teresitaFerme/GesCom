package es.ucm.fdi.gescom.features.ajustes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;

public class AjustesActivity extends BaseActivity implements AjustesView {
    private AjustesPresenter mPresenter;
    private SwitchCompat mModoOscuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Ajustes");

        mPresenter = new AjustesPresenter(this);

        mModoOscuro = findViewById(R.id.switch_modo_oscuro);
        mModoOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changeMode();
            }
        });
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}