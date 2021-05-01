package es.ucm.fdi.gescom.features.userdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;
import es.ucm.fdi.gescom.features.userdisplay.change_password.ChangePasswordActivity;

public class UserDisplayActivity extends BaseActivity implements UserDisplayView {
    private UserDisplayPresenter mPresenter;
    private Toolbar toolbar;
    private TextView mUsername, mUserRole, mCerrarSesion, mChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        getIntent();

        mPresenter = new UserDisplayPresenter(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Tu cuenta");
        //TODO poner a la derecha del toolbar el logout


        mUsername = findViewById(R.id.userDisplay_username);
        mUserRole = findViewById(R.id.userDisplay_role);

        mChangePassword = findViewById(R.id.userDisplay_change_password);
        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changePassword();
            }
        });

        mCerrarSesion = findViewById(R.id.userDisplay_cerrar_sesion);

        mCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.logOut();
            }
        });

        mPresenter.getUser();
    }

    public void goBack() {
        finish();
    }

    //TODO override el onBackPressed con finish, aunq no queda demasiado fluido, si lo dejamos así habría que poner un loader


    @Override
    public void setUserInfo(String username, String role) {
        mUsername.setText(username);
        mUserRole.append(role);
    }

    @Override
    public void logOut() {
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void changePassword() {
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

}