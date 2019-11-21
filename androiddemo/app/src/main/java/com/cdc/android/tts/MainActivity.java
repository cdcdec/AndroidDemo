package com.cdc.android.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cdc.mlog.MLog;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;
    private Button btnSpeak;
    private Button btnRecord;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btnSpeak=findViewById(R.id.btnSpeak);
        btnRecord=findViewById(R.id.btnRecord);
        tvText=findViewById(R.id.tvText);

        btnSpeak.setOnClickListener(this);
        btnRecord.setOnClickListener(this);

        initTts();
    }

    private void initTts(){
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                            && result != TextToSpeech.LANG_AVAILABLE){
                        Toast.makeText(MainActivity.this, "TTS暂时不支持这种语音的朗读！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSpeak:
                String text=tvText.getText().toString();

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String myUtteranceID = "myUtteranceID";
                   int a= textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, myUtteranceID);
                   MLog.e(a);
                } else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "myUtteranceID");
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, hashMap);
                }
              /*  textToSpeech.speak(tvText.getText().toString(),
                        TextToSpeech.QUEUE_ADD, null);*/

//setOnUtteranceProgressListener
                textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener(){

                    @Override
                    public void onStart(String utteranceId) {

                        MLog.e("开始:"+utteranceId);

                        if(textToSpeech!=null){
                            String defaultEngine=textToSpeech.getDefaultEngine();
                            Voice voice=textToSpeech.getDefaultVoice();
                            String voiceName=voice.getName();

                            MLog.e(defaultEngine,voiceName);
                        }

                    }

                    @Override
                    public void onDone(String utteranceId) {
                        MLog.e("完成:"+utteranceId);
                    }

                    @Override
                    public void onError(String utteranceId) {
                        MLog.e("错误:"+utteranceId);
                    }
                });



                break;

            case R.id.btnRecord:

                break;


        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null)
            textToSpeech.shutdown();
        super.onDestroy();
    }
}

//https://juejin.im/post/5d54caa3e51d453c2577b776