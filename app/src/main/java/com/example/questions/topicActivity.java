package com.example.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class topicActivity extends AppCompatActivity {

    TextView tv1, tv2;
    CardView cd1, cd2;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        cd1 = findViewById(R.id.cd1);
        cd2 = findViewById(R.id.cd2);

        databaseReference = FirebaseDatabase.getInstance().getReference("questions-fd355-default-rtdb");


    }
}