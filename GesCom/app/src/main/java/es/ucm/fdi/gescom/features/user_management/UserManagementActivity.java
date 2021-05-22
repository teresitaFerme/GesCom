package es.ucm.fdi.gescom.features.user_management;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button mAddUserButton;

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

        mAddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    @Override
    public void bindViews() {
        mPresenter = new UserManagementPresenter(this);
        mRecycler= findViewById(R.id.recycler_usuarios);
        mToolbar = findViewById(R.id.toolbar_users);
        mAddUserButton = findViewById(R.id.button_add_user);
    }

    @Override
    public void populateRecyclerView() {
        mUserList = mPresenter.getCommunityUsers();
        if(mUserList.size() != 0){
                UsersAdapter usersAdapter = new UsersAdapter(this, mUserList, this);
                mRecycler.setAdapter(usersAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void addUser() {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void editUser(int position) {
        Intent intent = new Intent(this, EditUserActivity.class);
        intent.putExtra("user", mUserList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void deleteUser(int userid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Estás seguro de que quieres eliminar a este usuario?");
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.validateDeleteUser(userid);
                populateRecyclerView();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing
            }
        });
        alertDialog.show();
    }

    public void onClick(int position, boolean edit, boolean delete) {
        if(edit){
            editUser(position);
        }else if(delete){
            deleteUser(mUserList.get(position).getId());
        }
    }
}