package es.ucm.fdi.gescom.features.userdisplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.base.BasePresenter;
import es.ucm.fdi.gescom.features.principal.PrincipalActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDisplayActivity extends BaseActivity implements UserDisplayView {
    private UserDisplayPresenter mPresenter;
    private Toolbar toolbar;
    private TextView mUsername, mUserRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        getIntent();

        mPresenter = new UserDisplayPresenter(this);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView tv = toolbar.findViewById(R.id.title);
        tv.setText("Tu cuenta");
        //TODO poner a la derecha del toolbar el logout


        mUsername = findViewById(R.id.textView_username);
        mUserRole = findViewById(R.id.textView_role);

        mPresenter.getUser();
    }

    public void goBack() {
        finish();
    }

    //TODO override el onBackPressed con finish, aunq no queda demasiado fluido, si lo dejamos así habría que poner un loader


    @Override
    public void setUserInfo(String username, String role) {
        mUsername.setText(username);
        mUserRole.append(role);
    }

}