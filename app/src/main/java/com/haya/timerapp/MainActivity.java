package com.haya.timerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView TimerView = findViewById(R.id.timer_view);
        Button StartPauseButton = findViewById(R.id.start_pause_button);
        Button RsetButton = findViewById(R.id.reset_button);

        StartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("クリックテスト", "出力確認");



            }
        });
    }
}