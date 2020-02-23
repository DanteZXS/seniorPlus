package com.example.seniorplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //前往speech recognizer的button
    private Button speechRecognizerBtn;
    private Button chatRoomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speechRecognizerBtn = findViewById(R.id.speech_recognizer);
        speechRecognizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SpeechRecognizerActivity.class);
                startActivity(intent);
            }
        });

        chatRoomBtn = findViewById(R.id.chat_room); chatRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityChatMain.class);
                startActivity(intent);
           }
        });

    }
}
