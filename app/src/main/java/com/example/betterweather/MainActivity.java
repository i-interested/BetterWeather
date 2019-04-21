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
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();


        ImageButton buttonRefresh = findViewById(R.id.buttonRefresh);
        ImageButton buttonSettings = findViewById(R.id.buttonSettings);
        buttonRefresh.setOnClickListener((l) -> {
                    Toast.makeText(getApplicationContext(), "buttonRefresh->onClick()", Toast.LENGTH_SHORT).show();
                    logLifeCycle("buttonRefresh->onClick()");
                }
        );

        buttonSettings.setOnClickListener((l) -> {
                    Toast.makeText(getApplicationContext(), "buttonSettings->onClick()", Toast.LENGTH_SHORT).show();
                    logLifeCycle("buttonSettings->onClick()");
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onDestroy()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        logLifeCycle("onSaveInstanceState()");
    }

    private void logLifeCycle(String msg) {
        Log.d("LifeCycle", msg);
    }
}
