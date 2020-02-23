package com.example.seniorplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //前往speech recognizer的button
    private Button speechRecognizerBtn;

    private Button chatMainBtn;

    //前往sign up的button
    private Button btnSighUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speechRecognizerBtn = findViewById(R.id.speech_recognizer);
        chatMainBtn = findViewById(R.id.chat_main);
        btnSighUp.findViewById(R.id.btnSignUp);



        speechRecognizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SpeechRecognizerActivity.class);
                startActivity(intent);
            }
        });


         chatMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityChatMain.class);
                startActivity(intent);
           }});

        btnSighUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

        });

    }
}
