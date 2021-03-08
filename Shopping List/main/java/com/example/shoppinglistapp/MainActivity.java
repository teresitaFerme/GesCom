package com.example.shoppinglistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView[] tvs = new TextView[10];
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String concatenador = "textView";
        tvs[0] = findViewById(R.id.textView1);
        tvs[1] = findViewById(R.id.textView2);
        tvs[2] = findViewById(R.id.textView3);
        tvs[3] = findViewById(R.id.textView4);
        tvs[4] = findViewById(R.id.textView5);
        tvs[5] = findViewById(R.id.textView6);
        tvs[6] = findViewById(R.id.textView7);
        tvs[7] = findViewById(R.id.textView8);
        tvs[8] = findViewById(R.id.textView9);
        tvs[9] = findViewById(R.id.textView10);


        if (savedInstanceState != null) {//esto tambien puede hacerse en onRestoreInstanceState(), pero en onCreate es el método preferido
            boolean[] isVisible =  savedInstanceState.getBooleanArray("reply_visible");
            String[] valoress =  savedInstanceState.getStringArray("reply");
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            for(int i = 0; i < 10; i++) {
                if(isVisible[i] == true){
                    tvs[i].setText(valoress[i].toString());
                    tvs[i].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void add_item(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String alimento = data.getStringExtra(SecondActivity.ALIMENTO);
                int contador = 0;
                for(int i = 0; i < 10; i++) {
                    if(tvs[i].getText() == ""){
                        contador = i;
                        break;
                    }
                }
                tvs[contador].setText(alimento);
                tvs[contador].setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // If the heading is visible, message needs to be saved.
        // Otherwise we're still using default layout.
        boolean[] guardar = new boolean[10];
        String[] valores = new String[10];
        for(int i = 0; i < 10 ; i++){
            if (tvs[i].getVisibility() == View.VISIBLE) {
                guardar[i] = true;
                valores[i] = tvs[i].getText().toString();
            }
        }
        outState.putBooleanArray("reply_visible", guardar);
        outState.putStringArray("reply", valores);
    }
}