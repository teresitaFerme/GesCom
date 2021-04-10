package es.ucm.fdi.gescom.features.register_comunidad.initialize_users;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;


public class UserInitializationActivity extends BaseActivity implements UsersInitializationView{
    private UsersInitializationPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private ArrayList<InitializableUser> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_initialization);

        Intent intent = getIntent();
        int numhouses = Integer.parseInt(intent.getStringExtra("numHouses"));
        mPresenter = new UsersInitializationPresenter(this);

        mRecyclerView = findViewById(R.id.initialize_users_recyclerView);

        mUsers = InitializableUser.createContactsList(numhouses);
        UsersAdapter usersAdapter = new UsersAdapter(mUsers);
        mRecyclerView.setAdapter(usersAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }



}