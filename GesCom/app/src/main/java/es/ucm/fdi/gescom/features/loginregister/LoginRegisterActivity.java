package es.ucm.fdi.gescom.features.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.dashBoard.DashBoardActivity;
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
        bindViews();

        mPresenter = new LoginRegisterPresenter(this);

        mRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openUserRegistration();
            }
        });

        mRegisterCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openCommunityRegistration();
            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.loginregister_bad_credentials).setVisibility(View.GONE);
                findViewById(R.id.loginregister_not_filled).setVisibility(View.GONE);
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
        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {
        findViewById(R.id.loginregister_bad_credentials).setVisibility(View.VISIBLE);
        mUsername.setText("");
        mPassword.setText("");
    }

    @Override
    public void fillingFailure() {
        findViewById(R.id.loginregister_not_filled).setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void bindViews() {
        mRegisterUser = findViewById(R.id.button_register_user_selected);
        mRegisterCommunity = findViewById(R.id.button_register_community_selected);
        mUsername = findViewById(R.id.editText_user);
        mPassword = findViewById(R.id.editText_password);
        mLogIn = findViewById(R.id.button_login);
    }
}