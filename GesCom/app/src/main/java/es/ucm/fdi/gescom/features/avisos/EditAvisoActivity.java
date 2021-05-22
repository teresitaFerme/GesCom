package es.ucm.fdi.gescom.features.avisos;

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
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabase;
import es.ucm.fdi.gescom.sqlite.CommunitiesDatabaseHelper;

public class EditAvisoActivity extends BaseActivity implements BaseView {
    private EditText asunto, descripcion;
    private Button modify;
    private int avisoID;
    private Toolbar mToolbar;
    private CommunitiesDatabaseHelper mCommunitiesDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_aviso);
        mCommunitiesDBHelper = new CommunitiesDatabaseHelper(this);

        bindViews();
        Intent intent = getIntent();
        avisoID = intent.getIntExtra("user", -1);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = mToolbar.findViewById(R.id.title);
        title.setText("Editar usuario");

        retrieveData();

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAviso();
            }
        });
    }

    private void updateAviso() {
        if (asunto.getText().length() != 0 && descripcion.getText().length() != 0) {
            SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_TITLE, asunto.getText().toString());
            values.put(CommunitiesDatabase.Avisos.COLUMN_NAME_BODY, descripcion.getText().toString());

            String selection = CommunitiesDatabase.Avisos._ID + " = ?";
            String[] selectionArgs = {String.valueOf(avisoID)};
            db.update(
                    CommunitiesDatabase.Avisos.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
            );

        }
        Intent intent = new Intent(this, AvisosActivity.class);
        startActivity(intent);
    }

    private void retrieveData() {
        SQLiteDatabase db = mCommunitiesDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                CommunitiesDatabase.Avisos.COLUMN_NAME_TITLE,
                CommunitiesDatabase.Avisos.COLUMN_NAME_BODY
        };
        String selection = CommunitiesDatabase.Avisos._ID + " = ?";
        String[] selectionArgs = {String.valueOf(avisoID)};
        Cursor cursor = db.query(
                CommunitiesDatabase.Avisos.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.moveToFirst()){
            asunto.setText(cursor.getString(1));
            descripcion.setText(cursor.getString(2));
        }
    }

    @Override
    public void bindViews() {
        asunto = findViewById(R.id.change_aviso_asunto);
        descripcion = findViewById(R.id.change_aviso_descripcion);
        modify = findViewById(R.id.button_change_aviso);
        mToolbar = findViewById(R.id.toolbar_change_aviso);
    }
}