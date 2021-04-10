package es.ucm.fdi.gescom.features.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.login.LoginActivity;
import es.ucm.fdi.gescom.features.register_comunidad.RegisterActivity;

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {
    private LoginRegisterPresenter mPresenter;
    private Button mLogin, mRegisterCommunity, mRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        getIntent();

        mPresenter = new LoginRegisterPresenter(this);

        mLogin = findViewById(R.id.button_login_selected);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openLogin();
            }
        });

        mRegisterUser = findViewById(R.id.button_register_user_selected);
        mRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openUserRegistration();
            }
        });

        mRegisterCommunity = findViewById(R.id.button_register_community_selected);
        mRegisterCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openCommunityRegistration();
            }
        });

    }

    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void registerCommunity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void registerUser() {
        //TODO: meter aqui la pag de registrar usuario
    }
}