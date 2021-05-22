package es.ucm.fdi.gescom.features.user_management.new_user;

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
import es.ucm.fdi.gescom.features.user_management.UserManagementActivity;

public class AddUserActivity extends BaseActivity implements AddUserView{
    private EditText dni, localizador;
    private Toolbar mToolbar;
    private AddUserPresenter userManagementPresenter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        bindViews();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Añadir un usuario");

        userManagementPresenter = new AddUserPresenter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userManagementPresenter.validate(dni.getText().toString(), localizador.getText().toString());
            }
        });

    }

    @Override
    public void bindViews() {
        dni = findViewById(R.id.user_initialize_dni);
        localizador = findViewById(R.id.user_initialize_localizer);
        mToolbar = findViewById(R.id.toolbar_add_user);
        button = findViewById(R.id.button_add_user_add_user);
    }

    @Override
    public void wrongData(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void initSuccessful() {
        Intent intent = new Intent(this, UserManagementActivity.class);
        startActivity(intent);
    }

    @Override
    public void initFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }


}