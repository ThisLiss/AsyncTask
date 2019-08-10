package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements AsyncTask.OnAsync {

    private Button btnStart;
    private Button btnStop;
    private ProgressBar progBar;
    public AsyncTask async;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progBar.setMax(100);
                async = new AsyncTask(MainActivity.this);
                async.execute("www.ya.ru");
                btnStart.setEnabled(false);
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
