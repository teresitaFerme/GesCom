package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;


public class UserInitializationActivity extends BaseActivity implements UsersInitializationView{
    private UsersInitializationPresenter mPresenter;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_initialization);
        bindViews();

        Intent intent = getIntent();
        String nombreAdmin = intent.getStringExtra("nombreAdmin");
        String comName = intent.getStringExtra("comunidadNombre");
        String password = intent.getStringExtra("password");
        String dni = intent.getStringExtra("dni");

        mPresenter = new UsersInitializationPresenter(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validateUsers(null, comName, nombreAdmin, password, dni);
            }
        });
    }

    @Override
    public void validationSuccess() {
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void validationFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void bindViews() {
        mButton = findViewById(R.id.button_end_register);
    }
}