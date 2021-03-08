package com.example.shoppinglistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    public static String ALIMENTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void add_item(View view) {
        int id = view.getId();
        Intent from_main = getIntent();
        Intent intent = new Intent(this, MainActivity.class);

        if(id == R.id.button_galletas){
            intent.putExtra(ALIMENTO, "Galletas");
        }else if(id == R.id.button_limones){
            intent.putExtra(ALIMENTO, "Limones");
        }else if(id == R.id.button_leche){
            intent.putExtra(ALIMENTO, "Leche");
        }else if(id == R.id.button_choco){
            intent.putExtra(ALIMENTO, "Chocolate");
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}