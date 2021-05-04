package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;


public class UserInitializationActivity extends BaseActivity implements UsersInitializationView{
    private UsersInitializationPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private ArrayList<InitializableUser> mUsers = new ArrayList<>();
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_initialization);
        bindViews();

        Intent intent = getIntent();
        int numhouses = Integer.parseInt(intent.getStringExtra("numHouses"));
        String nombreAdmin = intent.getStringExtra("nombreAdmin");
        String comName = intent.getStringExtra("comunidadNombre");
        String password = intent.getStringExtra("password");
        String dni = intent.getStringExtra("dni");

        mPresenter = new UsersInitializationPresenter(this);


        mUsers = InitializableUser.createContactsList(numhouses);
        UsersAdapter usersAdapter = new UsersAdapter(this, mUsers);
        mRecyclerView.setAdapter(usersAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.validateUsers(mUsers, comName, nombreAdmin, password, dni);
            }
        });
    }

    @Override
    public void validationSuccess() {
        Toast toast = Toast.makeText(this, "Se han insertado correctamente los usuarios", Toast.LENGTH_LONG);
        toast.show();
        //TODO dirigir al admin a la pantalla principal
    }

    @Override
    public void validationFailure(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void bindViews() {
        mRecyclerView = findViewById(R.id.initialize_users_recyclerView);
        mButton = findViewById(R.id.button_end_register);
    }
}