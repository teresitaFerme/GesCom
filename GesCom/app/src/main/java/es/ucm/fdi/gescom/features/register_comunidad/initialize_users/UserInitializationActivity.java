package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.os.Bundle;
import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;

public class UserInitializationActivity extends BaseActivity implements UsersInitializationView{
    private UsersInitializationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_initialization);
        mPresenter = new UsersInitializationPresenter(this);
    }
}