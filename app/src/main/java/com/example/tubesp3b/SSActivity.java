package com.example.tubesp3b;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SSActivity extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash);

        final Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SSActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
