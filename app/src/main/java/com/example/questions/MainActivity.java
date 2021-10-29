package com.example.questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Questions> listOfQuestions;

    DatabaseReference databaseReference;
    //List<Questions> allQuestions;
    Questions question;
    int index = 0;
    TextView qn, opA, opB, opC, opD, answer;
    CardView cardA, cardB, cardC, cardD;
    //Button btn;
    static int crct = 0;
    //int wrong = 0;
    private long pressedTime;

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qn = findViewById(R.id.question);
        opA = findViewById(R.id.optionA);
        opB = findViewById(R.id.optionB);
        opC = findViewById(R.id.optionC);
        opD = findViewById(R.id.optionD);

        cardA = findViewById(R.id.oA);
        cardB = findViewById(R.id.oB);
        cardC = findViewById(R.id.oC);
        cardD = findViewById(R.id.oD);

        listOfQuestions = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");
       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(snapshot.exists()) {
                         question = dataSnapshot.getValue(Questions.class);
                       // String checkQuestion=questions.Questions;
                        Log.d("checkQue","checkQuestion");
                        listOfQuestions.add(question);
                        Values();
                        //allQuestions = listOfQuestions;
                        question = listOfQuestions.get(index);
                        setData();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Something wrong",Toast.LENGTH_LONG).show();
                        Log.d("checkQue","checkQuestion");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Log.w("error", "Failed to read value.", error.toException());
            }
        });

        //listOfQuestions.add(new Questions("Which is the capital of india?", "Delhi", "Chennai", "Mumbai", "Kolkata","Delhi"));
        //listOfQuestions.add(new Questions("Who is called peoples president?", "Modi", "Manmohan signh", "APJ abdul kalam", "Patel", "APJ abdul kalam"));

        /*for(int i=0;i<(listOfQuestions.size()-1);i++) {
            Values();
            //allQuestions = listOfQuestions;
            question = listOfQuestions.get(i+1);
            setData();
        }*/
        //btn = findViewById(R.id.btn);

    }
    public void Values(){
        qn = findViewById(R.id.question);
        opA = findViewById(R.id.optionA);
        opB = findViewById(R.id.optionB);
        opC = findViewById(R.id.optionC);
        opD = findViewById(R.id.optionD);

        cardA = findViewById(R.id.oA);
        cardB = findViewById(R.id.oB);
        cardC = findViewById(R.id.oC);
        cardD = findViewById(R.id.oD);

    }

    public void setData(){
        qn.setText(question.getQuestions());
        opA.setText(question.getOptA());
        opB.setText(question.getOptB());
        opC.setText(question.getOptC());
        opD.setText(question.getOptD());
    }

    public void correct(){
        crct++;
        if (index < listOfQuestions.size()-1) {
            index++;
            question = listOfQuestions.get(index);
            setData();
            enableButton();
        }
        else{
            end();
        }
    }

    public void wrong(){
        //wrong++;
        if (index < listOfQuestions.size()-1){
            index++;
            question = listOfQuestions.get(index);
            setData();
            enableButton();
        }
        else{
            end();
        }
    }

    private void end(){
        Intent i = new Intent(MainActivity.this,Last.class);
        i.putExtra("Correct",crct);
        startActivity(i);
    }
    public void enableButton(){
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }
    public void disableButton(){
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
    }

    public void optionA(View v){
        if(question.getOptA().equals(question.getAns())){
                correct();
        }
        else{
            wrong();
        }
    }

    public void optionB(View v){
        if(question.getOptB().equals(question.getAns())){
                correct();
        }
        else{
            wrong();
        }
    }

    public void optionC(View v){
        if(question.getOptC().equals(question.getAns())){
                correct();
        }
        else{
            wrong();
        }
    }

    public void optionD(View v){
        if(question.getOptD().equals(question.getAns())){
                correct();
        }
        else{
            wrong();
        }
    }

}