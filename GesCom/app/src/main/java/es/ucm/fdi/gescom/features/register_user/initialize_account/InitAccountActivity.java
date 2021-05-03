package es.ucm.fdi.gescom.features.register_user.initialize_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.features.principal.PrincipalActivity;

public class InitAccountActivity extends BaseActivity implements InitAccountView{
    private InitAccountPresenter mPresenter;
    private EditText mUsername, mPass, mPassRepeat;
    private Button mButton;
    private String dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_account);
        Intent intent = getIntent();
        bindViews();
        dni = intent.getStringExtra("dni");

        mPresenter = new InitAccountPresenter(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validate(mUsername.getText().toString(), mPass.getText().toString(), mPassRepeat.getText().toString(), dni);
            }
        });
    }

    @Override
    public void initSuccessful() {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    @Override
    public void initFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void bindViews() {
        mUsername = findViewById(R.id.editText_init_account_username);
        mPass = findViewById(R.id.editText_init_account_password);
        mPassRepeat = findViewById(R.id.editText_init_account_repite_password);
        mButton = findViewById(R.id.button_init_account);
    }
}