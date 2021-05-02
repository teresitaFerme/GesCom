package es.ucm.fdi.gescom.features.userdisplay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.GesComApp;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;
import es.ucm.fdi.gescom.features.userdisplay.change_password.ChangePasswordActivity;

public class UserDisplayActivity extends BaseActivity implements UserDisplayView {
    private UserDisplayPresenter mPresenter;
    private Toolbar toolbar;
    private TextView mUsername, mUserRole, mCerrarSesion, mChangePassword, mComunidad, mVerClave;
    private LinearLayout mAdminSettingsContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        getIntent();
        bindViews();

        mPresenter = new UserDisplayPresenter(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Tu cuenta");
        //TODO poner a la derecha del toolbar el logout

        if(!mPresenter.checkAdmin()){
            mAdminSettingsContainer.setVisibility(View.GONE);
        }

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changePassword();
            }
        });


        mCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.logOut();
            }
        });

        mVerClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage(GesComApp.getComunidad().getKey());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Continuar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        mPresenter.getUser();
    }
    //TODO override el onBackPressed con finish, aunq no queda demasiado fluido, si lo dejamos así habría que poner un loader

    @Override
    public void bindViews() {
        mVerClave = findViewById(R.id.userDisplay_ver_clave_comunidad);
        mUsername = findViewById(R.id.userDisplay_username);
        mUserRole = findViewById(R.id.userDisplay_role);
        mComunidad = findViewById(R.id.userDisplay_community);
        mCerrarSesion = findViewById(R.id.userDisplay_cerrar_sesion);
        mChangePassword = findViewById(R.id.userDisplay_change_password);
        toolbar = findViewById(R.id.toolbar);
        mAdminSettingsContainer = findViewById(R.id.userDisplay_admin_functionalities_container);
    }

    @Override
    public void setUserInfo(String username, String role, String comunidad) {
        mUsername.setText(username);
        mUserRole.append(role);
        mComunidad.setText("Comunidad: " + comunidad);
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