package es.ucm.fdi.gescom.features.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.login.LoginActivity;
import es.ucm.fdi.gescom.features.register.RegisterActivity;

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {
    private LoginRegisterPresenter mPresenter;
    private Button mLogin, mRegister;

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

        mRegister = findViewById(R.id.button_register_selected);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openRegistration();
            }
        });

    }

    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}