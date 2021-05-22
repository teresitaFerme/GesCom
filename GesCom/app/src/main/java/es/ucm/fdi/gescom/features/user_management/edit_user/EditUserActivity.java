package es.ucm.fdi.gescom.features.user_management.edit_user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.base.BaseActivity;
import es.ucm.fdi.gescom.base.BaseView;
import es.ucm.fdi.gescom.features.user_management.UserManagementActivity;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class EditUserActivity extends BaseActivity implements BaseView {
    EditText username, dni, localizador;
    Button newUser;
    private Toolbar mToolbar;
    private CommunitiesDatabaseHelper mCommunitiesDBHelper;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(this);
        bindViews();

        Intent intent = getIntent();
        userID = intent.getIntExtra("user", -1);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Editar usuario");

        retrieveData();

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

    }

    @Override
    public void bindViews() {
        username = findViewById(R.id.user_edit_name);
        dni = findViewById(R.id.user_edit_dni);
        localizador = findViewById(R.id.user_edit_localizador);
        mToolbar = findViewById(R.id.toolbar_new_user);
        newUser = findViewById(R.id.button_new_user_add_user);
    }

    private void retrieveData(){
        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.User.COLUMN_NAME_USERNAME,
                CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER,
                CommunitiesDatabase.User.COLUMN_NAME_DNI
        };
        String selection = CommunitiesDatabase.User._ID + " = ?";
        String[] selectionArgs = {String.valueOf(userID)};
        Cursor cursor = db.query(
                CommunitiesDatabase.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.moveToFirst()){
            username.setText(cursor.getString(1));
            username.setEnabled(false);
            localizador.setText(cursor.getString(2));
            dni.setText(cursor.getString(3));
        }
    }

    private void updateUser(){
        if (dni.getText().length() != 0 && localizador.getText().length() != 0) {
            if(esDni(dni.getText().toString())) {
                SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

                ContentValues values = new ContentValues();
                values.put(CommunitiesDatabase.User.COLUMN_NAME_LOCALIZER, localizador.getText().toString());
                values.put(CommunitiesDatabase.User.COLUMN_NAME_DNI, dni.getText().toString());

                String selection = CommunitiesDatabase.User._ID + " = ?";
                String[] selectionArgs = {String.valueOf(userID)};
                db.update(
                        CommunitiesDatabase.User.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );
            }
        }
        Intent intent = new Intent(this, UserManagementActivity.class);
        startActivity(intent);
    }

    private static boolean esDni(String cadena){
        if(cadena.length() != 9){
            return false;
        }else{
            for (int x = 0; x < cadena.length() - 1; x++) {
                char c = cadena.charAt(x);
                if (x < 8 && !(c >= '0' && c <='9')) {
                    return false;
                }
            }
            char c = cadena.charAt(8);
            if(!(c >= 'A' && c <= 'Z')){
                return false;
            }
            return true;
        }
    }
}