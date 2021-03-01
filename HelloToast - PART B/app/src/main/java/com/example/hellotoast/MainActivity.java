package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private Button b_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        b_reset = (Button) findViewById(R.id.zero);
    }

    public void show_toast(View view) {
        Toast toast = Toast.makeText(this, "Hello Toast",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void show_count(View view) {
        mCount++;
        b_reset.setBackgroundColor(Color.RED);
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
            if (mCount % 2 == 0)   view.setBackgroundColor(Color.BLACK);
            else view.setBackgroundColor(Color.BLUE);
        }
    }

    public void reset(View view) {
        mCount = 0;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
            view.setBackgroundColor(Color.GRAY);
        }
    }
}