package com.example.betterweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        logLifeCycle(instanceState + " - onCreate()");

        SetListners();
    }

    private void SetListners() {
        ImageButton buttonRefresh = findViewById(R.id.buttonRefresh);
        ImageButton buttonSettings = findViewById(R.id.buttonSettings);
        buttonRefresh.setOnClickListener((l) -> logLifeCycle("buttonRefresh->onClick()"));
        buttonSettings.setOnClickListener((l) -> logLifeCycle("buttonSettings->onClick()"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        logLifeCycle("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logLifeCycle("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logLifeCycle("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logLifeCycle("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logLifeCycle("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifeCycle("onDestroy()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logLifeCycle("onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logLifeCycle("onSaveInstanceState()");
    }

    private void logLifeCycle(String msg) {
        Toast.makeText(getApplicationContext(), "msg", Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle", msg);
    }
}
