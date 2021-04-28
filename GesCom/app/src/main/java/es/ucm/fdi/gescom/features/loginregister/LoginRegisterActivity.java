package es.ucm.fdi.gescom.features.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.principal.PrincipalActivity;
import es.ucm.fdi.gescom.features.register_comunidad.RegisterActivity;
import es.ucm.fdi.gescom.features.register_user.RegisterUserActivity;

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterView {
    private LoginRegisterPresenter mPresenter;
    private TextView mRegisterCommunity, mRegisterUser;
    private EditText mUsername, mPassword;
    private Button mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        getIntent();

        mPresenter = new LoginRegisterPresenter(this);

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

        mUsername = findViewById(R.id.editText_user);
        mPassword = findViewById(R.id.editText_password);

        mLogIn = findViewById(R.id.button_login);
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validateLogin(String.valueOf(mUsername.getText()), String.valueOf(mPassword.getText()));
            }
        });

    }

    @Override
    public void registerCommunity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void registerUser() {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccessful() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        Toast toast = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
        toast.show();
        mUsername.setText("");
        mPassword.setText("");
    }

    @Override
    public void fillingFailure() {
        Toast toast = Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        
    }
}