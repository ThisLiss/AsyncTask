package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AsyncTask.OnAsync {

    private Button btnStart;
    private Button btnStop;
    private ProgressBar progBar;
    AsyncTask async;
    String inputString = "www.ya.ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         async = new AsyncTask(this);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                async.execute(inputString);
                btnStart.setEnabled(false);
             }
                catch (IllegalStateException e){
                    Toast.makeText(MainActivity.this, "Задача уже была выполнена =(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(true);
                progBar.setMax(0);
                async.cancel(true);
            }
        });
        progBar = findViewById(R.id.progBar);
    }

    @Override
    public void onProgBar(int pos) {
        progBar.setProgress(pos);
    }

    @Override
    public void onEnd(String url) {
        btnStart.setEnabled(true);
        progBar.setProgress(0);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
