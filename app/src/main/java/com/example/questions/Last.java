package com.example.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Last extends AppCompatActivity {

    int correct;
    TextView tv;
    private long pressedTime;

    @Override
    public void onBackPressed() {

        if ( pressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        correct = getIntent().getIntExtra("Correct",0);

        tv = findViewById(R.id.tv1);

        tv.setText("You scored "+correct+"/5");

    }
}