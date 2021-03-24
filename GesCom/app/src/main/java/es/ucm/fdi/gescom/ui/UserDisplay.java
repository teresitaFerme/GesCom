package es.ucm.fdi.gescom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import es.ucm.fdi.gescom.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDisplay extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        getIntent();

        toolbar = this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TextView tv = findViewById(R.id.title_user);
        tv.setText("userName");

    }


    public void goBack(View view) {
        finish();
    }
}