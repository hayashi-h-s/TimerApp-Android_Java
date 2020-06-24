package com.haya.timerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 30000;   //タイマー設定 単位 ミリ秒   final 変更できない設定値

    private TextView mTextViewCountDown;  //アクセス修飾子
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

//    定数、START_TIME_IN_MILLISを変数として使えるようにしている
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private boolean mTimerRun;   // OS内の CallTimeクラス

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset  = findViewById(R.id.buttonreset);

        mButtonReset.setVisibility(View.INVISIBLE);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRun){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

//        最終的に４桁の数字にしている
        updateCountDownText();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mButtonReset.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                mTimerRun = false;
                mButtonStartPause.setText("スタート");
//                mButtonReset.setVisibility(View.INVISIBLE);    //非表示
            }
        }.start();

        mTimerRun = true;   // タイマー作動中
        mButtonStartPause.setText("一時停止");
//        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRun = false;
        mButtonStartPause.setText("スタート");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.VISIBLE);
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int)(mTimeLeftInMillis/1000)/60;
        int seconds = (int)(mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
}