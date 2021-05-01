package es.ucm.fdi.gescom.features.userdisplay.change_password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.userdisplay.UserDisplayActivity;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView{
    private Toolbar toolbar;
    private EditText mPassword, mNewPassword, mNewPasswordRepeat;
    private Button mButton;
    private ChangePasswordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        bindViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Cambiar contraseña");

        mPresenter = new ChangePasswordPresenter(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validateData(String.valueOf(mPassword.getText()),String.valueOf(mNewPassword.getText()) ,String.valueOf(mNewPasswordRepeat.getText()));
            }
        });

    }

    @Override
    protected void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        mPassword = findViewById(R.id.change_password_contrasena_actual);
        mNewPassword = findViewById(R.id.change_password_contrasena_nueva);
        mNewPasswordRepeat = findViewById(R.id.change_password_contrasena_nueva_repeat);
        mButton = findViewById(R.id.change_password_button);
    }

    @Override
    public void noMatchingPasswords() {
        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillingFailure() {
        Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void notActualPassword() {
        Toast.makeText(this, "La contraseña no es correcta", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launchUserDisplay() {
        Intent intent = new Intent(this, UserDisplayActivity.class);
        startActivity(intent);
    }

    @Override
    public void samePassword() {
        Toast.makeText(this, "La nueva contraseña debe ser diferente a la actual", Toast.LENGTH_SHORT).show();
    }
}