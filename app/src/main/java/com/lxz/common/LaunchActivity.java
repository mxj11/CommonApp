package com.lxz.common;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startToMainActivity();
            }
        }, 2000);
    }

    private void startToMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }
}
