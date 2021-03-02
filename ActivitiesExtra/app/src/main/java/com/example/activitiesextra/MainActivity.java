package com.example.activitiesextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show_3(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra(EXTRA_MESSAGE, getString(R.string.Text3));
        startActivity(intent);
    }

    public void show_1(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, getString(R.string.Text1));
        startActivity(intent);
    }

    public void show_2(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, getString(R.string.Text2));
        startActivity(intent);
    }
}