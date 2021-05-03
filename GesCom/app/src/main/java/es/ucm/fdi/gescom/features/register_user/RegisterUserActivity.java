package es.ucm.fdi.gescom.features.register_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.register_user.initialize_account.InitAccountActivity;

public class RegisterUserActivity extends BaseActivity implements RegisterUserView{
    private RegisterUserPresenter mRegisterUserPresenter;
    private Button mButton;
    private EditText mDni, mClave;

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
            }
        });
    }

    public void correctData(){
        //TODO launch here the activity to change password and username, los username no pueden tener formato de dni
        Toast toast = Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, InitAccountActivity.class);
        intent.putExtra("dni", mDni.getText().toString());
        startActivity(intent);
    }

    public void wrongData(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void bindViews() {
        mClave = findViewById(R.id.editText_clave);
        mDni = findViewById(R.id.editText_dni);
        mButton = findViewById(R.id.button_register_user);
    }
}