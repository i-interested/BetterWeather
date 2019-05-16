package com.example.betterweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class WeatherFragment extends Fragment {
    final Random random = new Random();
    private String[] winds = {"N", "S", "E", "W", "NW", "NE", "SE", "SW"};
    private String[] windsRu = {"С", "Ю", "В", "З", "СЗ", "СВ", "ЮВ", "ЮЗ"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        InitValues();
    }

    private void InitValues() {
        Activity activity = getActivity();
        TextView weather = activity.findViewById(R.id.text_weather_now);
        String weatherTemplate = activity.getString(R.string.main_weather_now);
        int weatherValue = random.nextInt(100) - 50;
        String weatherText = String.format(weatherTemplate, String.valueOf(weatherValue));
        weather.setText(weatherText);

        TextView humidity = activity.findViewById(R.id.text_humidity_now);
        String humidityTemplate = activity.getString(R.string.main_humidity);
        int humidityValue = random.nextInt(100);
        String humidityText = String.format(humidityTemplate, String.valueOf(humidityValue));
        humidity.setText(humidityText);

        TextView pressure = activity.findViewById(R.id.text_pressure_now);
        String pressureTemplate = activity.getString(R.string.main_pressure);
        int pressureValue = random.nextInt(100) + 680;
        String pressureText = String.format(pressureTemplate, String.valueOf(pressureValue));
        pressure.setText(pressureText);
        Locale ruLocale = new Locale("ru", "RU");
        TextView wind = activity.findViewById(R.id.text_wind_now);
        String windTemplate = activity.getString(R.string.main_wind);
        int windValue = random.nextInt(20);
        int idx = random.nextInt(winds.length);
        String windText = String.format(windTemplate, String.valueOf(windValue), Locale.getDefault().equals(ruLocale) ? windsRu[idx] : winds[idx]);
        wind.setText(windText);
    }
}
