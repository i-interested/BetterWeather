package com.example.betterweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mainRecyclerView;
    private MainWeatherAdapter mainWeatherAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String instanceState = savedInstanceState == null ? "Первый запуск!" : "Повторный запуск!";
        logLifeCycle(instanceState + " - onCreate()");

        initViewItems();
    }

    private void initViewItems() {
        findViews();

        mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mainWeatherAdapter = new MainWeatherAdapter(getString(R.string.main_part_of_day));
        mainRecyclerView.setAdapter(mainWeatherAdapter);
        swipeRefreshLayout.setOnRefreshListener(() ->
                new Handler().postDelayed(() -> {

                    swipeRefreshLayout.setRefreshing(false);
                }, 2000)
        );
    }

    private void findViews() {
        mainRecyclerView = findViewById(R.id.main_recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != Identifiers.SETTINGS_CODE || resultCode != Activity.RESULT_OK)
            return;

        if (data == null)
            return;

        ArrayList<String> cities = data.getStringArrayListExtra(Identifiers.CITIES);
        mainWeatherAdapter.setCities(cities);
        boolean showAdditional = data.getBooleanExtra(Identifiers.ADDITIONAL_INFO, true);
        mainWeatherAdapter.setLinearLayoutAdditionalInfoVisibility(showAdditional);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                settingsIntent.putExtra(Identifiers.CITIES, mainWeatherAdapter.getCities());
                settingsIntent.putExtra(Identifiers.ADDITIONAL_INFO, mainWeatherAdapter.getLinearLayoutAdditionalInfoVisibility());
                startActivityForResult(settingsIntent, Identifiers.SETTINGS_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle", msg);
    }
}
