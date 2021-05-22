package es.ucm.fdi.gescom.features.register_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.loginregister.LoginRegisterActivity;

public class RegisterUserActivity extends BaseActivity implements RegisterUserView{
    private RegisterUserPresenter mRegisterUserPresenter;
    private Button mButton;
    private EditText mDni, mClave, mUsername, mPass, mPassRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        bindViews();
        getIntent();
        mRegisterUserPresenter = new RegisterUserPresenter(this);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisterUserPresenter.validate(mDni.getText().toString(), mClave.getText().toString());
                mRegisterUserPresenter.validate(mUsername.getText().toString(), mPass.getText().toString(), mPassRepeat.getText().toString(), mDni.getText().toString());
            }
        });
    }

    public void wrongData(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void bindViews() {
        mClave = findViewById(R.id.editText_clave);
        mDni = findViewById(R.id.editText_dni);
        mButton = findViewById(R.id.button_init_account);
        mUsername = findViewById(R.id.editText_init_account_username);
        mPass = findViewById(R.id.editText_init_account_password);
        mPassRepeat = findViewById(R.id.editText_init_account_repite_password);
    }

    @Override
    public void initSuccessful() {
        Intent intent = new Intent(this, LoginRegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void initFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}