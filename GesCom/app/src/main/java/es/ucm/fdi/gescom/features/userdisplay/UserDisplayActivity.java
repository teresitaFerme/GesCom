package es.ucm.fdi.gescom.features.userdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.base.BasePresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDisplayActivity extends BaseActivity implements UserDisplayView {
    private UserDisplayPresenter mPresenter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        getIntent();

        mPresenter = new UserDisplayPresenter(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Tu cuenta");
        //TODO poner la flecha en la izq para volver hacia atrás y a la derecha del toolbar el logout
    }

    public void goBack(View view) {
        finish();
    }
}