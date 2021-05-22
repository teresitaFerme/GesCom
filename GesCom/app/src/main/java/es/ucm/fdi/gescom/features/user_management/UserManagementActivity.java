package es.ucm.fdi.gescom.features.user_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.datacache.User;
import es.ucm.fdi.gescom.features.user_management.edit_user.EditUserActivity;
import es.ucm.fdi.gescom.features.user_management.new_user.AddUserActivity;

public class UserManagementActivity extends BaseActivity implements UserManagementView {
    private RecyclerView mRecycler;
    private ArrayList<User> mUserList;
    private Toolbar mToolbar;
    private UserManagementPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        getIntent();

        bindViews();
        populateRecyclerView();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Gestionar usuarios");
    }

    @Override
    public void bindViews() {
        mPresenter = new UserManagementPresenter(this);
        mRecycler= findViewById(R.id.recycler_usuarios);
        mToolbar = findViewById(R.id.toolbar_users);
    }

    @Override
    public void populateRecyclerView() {
        mUserList = mPresenter.getCommunityUsers();
        if(mUserList.size() != 0){
                UsersAdapter usersAdapter = new UsersAdapter(this, mUserList);
                mRecycler.setAdapter(usersAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(this));
        }else{

        }
    }

    @Override
    public void addUser() {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void editUser() {
        Intent intent = new Intent(this, EditUserActivity.class);
        startActivity(intent);
    }


}