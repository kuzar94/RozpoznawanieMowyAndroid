package com.example.gnomechild.speech_text_arek;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView resultTEXT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTEXT = (TextView)findViewById(R.id.TVresult);
        String teext =" ";
    }
    public void onButtonClick(View v){
        if(v.getId()== R.id.imageButton){
            promptSpeechInput();

        }
    }
    public void promptSpeechInput(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            startActivityForResult(i, 100);
        }catch (ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this ,"Sorry, device doesn't speech", Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code, int result_code, Intent i) {
        super.onActivityResult(request_code, result_code, i);
        switch (request_code) {
            case 100:
                if (result_code == RESULT_OK && i != null) {
                    ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (resultTEXT.getText().toString().equals("wyłącz") ){
                        WebView webView = new WebView(this) ;
                        setContentView(webView);
                        webView.loadUrl("http://192.168.4.1/OFF");
                    }
                    if (resultTEXT.getText().toString().equals("włącz") ){
                        WebView webView = new WebView(this) ;
                        setContentView(webView);
                        webView.loadUrl("http://192.168.4.1/ON");
                    }
                    else
                        resultTEXT.setText(result.get(0));

                }
        }
    }
}








