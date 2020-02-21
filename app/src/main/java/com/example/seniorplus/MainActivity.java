package com.example.seniorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //前往speech recognizer的button
    private Button speechRecognizerBtn;
    //前往sign up的button
    private Button btnSighUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechRecognizerBtn = findViewById(R.id.speech_recognizer);
        btnSighUp.findViewById(R.id.btnSignUp);


        speechRecognizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SpeechRecognizerActivity.class);
                startActivity(intent);
            }
        });

        btnSighUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
