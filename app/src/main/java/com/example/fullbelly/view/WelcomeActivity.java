package com.example.fullbelly.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fullbelly.R;


public class WelcomeActivity extends AppCompatActivity {

    private static int TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent welcomeIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                Intent welcomeIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(welcomeIntent);
                finish();
            }
        },TIME_OUT);
    }
}

