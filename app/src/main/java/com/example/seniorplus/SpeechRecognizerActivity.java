package com.example.seniorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechRecognizerActivity extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;

    private Button btnSpeechToText;
    private TextView txtSpeechToText;
    private Button btnTextToSpeech;
    private EditText txtTextToSpeech;
    TextToSpeech textToSpeech;
    String sentence = "Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognizer);

        // Speech to text 相关
        txtSpeechToText = (TextView) findViewById(R.id.txtSpeechToText);
        btnSpeechToText = (Button) findViewById(R.id.btnSpeechToText);
        // Text to speech 相关
        btnTextToSpeech = findViewById(R.id.btnTextToSpeech);
        txtTextToSpeech = findViewById(R.id.txtTextToSpeech);

        //Speech to Text的逻辑实现
        btnSpeechToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启语音识别功能
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //设置模式，目前设置的是自由识别模式
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //提示语言开始文字，就是效果图上面的文字
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start your voice");
                //开始识别，这里检测手机是否支持语音识别并且捕获异常
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    btnSpeechToText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });


        //Text to Speech的逻辑实现
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.GERMAN);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        btnTextToSpeech.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        btnTextToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentence = txtTextToSpeech.getText().toString();
                textToSpeech.speak(sentence,TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    //接收返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && data != null) {
                    //返回结果是一个list，我们一般取的是第一个最匹配的结果
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechToText.setText(text.get(0));
                }
                break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }



}
