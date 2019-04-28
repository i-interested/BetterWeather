package com.example.betterweather;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLocationName;
    private LinearLayout linearLayoutAdditionalInfo;
    private FutureWeather[] futureWeathersRu = {
            new FutureWeather("+12°C", "29 апр.", R.drawable.sun),
            new FutureWeather("+14°C", "30 апр.", R.drawable.sun),
            new FutureWeather("+16°C", "1 мая", R.drawable.sun),
            new FutureWeather("+21°C", "2 мая", R.drawable.sun),
    };

    private FutureWeather[] futureWeathers = {
            new FutureWeather("+12°C", "Apr. 29", R.drawable.sun),
            new FutureWeather("+14°C", "Apr. 30", R.drawable.sun),
            new FutureWeather("+16°C", "May 1", R.drawable.sun),
            new FutureWeather("+21°C", "May 2", R.drawable.sun),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState = savedInstanceState == null ? "Первый запуск!" : "Повторный запуск!";
        logLifeCycle(instanceState + " - onCreate()");

        initViewItems();
    }

    private void initViewItems() {
        textViewLocationName = findViewById(R.id.location_name);
        linearLayoutAdditionalInfo = findViewById(R.id.additional_info);
        ImageButton buttonRefresh = findViewById(R.id.button_refresh);
        ImageButton buttonSettings = findViewById(R.id.button_settings);
        GridView gridView = findViewById(R.id.grid_view_feature_weather);
        Locale ruLocale = new Locale("ru","RU");
        FutureWeatherAdapter futureWeatherAdapter = new FutureWeatherAdapter(this,
                Locale.getDefault().equals(ruLocale) ? futureWeathersRu : futureWeathers);

        buttonRefresh.setOnClickListener((l) -> logLifeCycle("buttonRefresh->onClick()"));
        buttonSettings.setOnClickListener((l) -> {
            logLifeCycle("buttonSettings->onClick()");
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            settingsIntent.putExtra(Identifiers.LOCATION_NAME, textViewLocationName.getText());
            settingsIntent.putExtra(Identifiers.ADDITIONAL_INFO, linearLayoutAdditionalInfo.getVisibility() == View.VISIBLE);
            startActivityForResult(settingsIntent, Identifiers.SETTINGS_CODE);
        });

        gridView.setAdapter(futureWeatherAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != Identifiers.SETTINGS_CODE || resultCode != Activity.RESULT_OK)
            return;

        if (data == null)
            return;

        String locationName = data.getStringExtra(Identifiers.LOCATION_NAME);
        textViewLocationName.setText(locationName);
        boolean showAdditional = data.getBooleanExtra(Identifiers.ADDITIONAL_INFO, true);
        linearLayoutAdditionalInfo.setVisibility(showAdditional ? View.VISIBLE : View.GONE);
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
