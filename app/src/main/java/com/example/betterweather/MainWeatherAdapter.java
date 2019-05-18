package com.example.betterweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainWeatherAdapter extends RecyclerView.Adapter<MainWeatherAdapter.MainWeatherHolder> {

    private ArrayList<String> cities = new ArrayList<>(Arrays.asList("Barnaul", "Moscow"));
    private String dayTemplate;
    private boolean additionalInfoVisibility;

    public MainWeatherAdapter(String dayTemplate) {
        this.dayTemplate = dayTemplate;
        additionalInfoVisibility = true;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public boolean getLinearLayoutAdditionalInfoVisibility() {
        return additionalInfoVisibility;
    }

    public void setLinearLayoutAdditionalInfoVisibility(boolean showAdditional) {
        additionalInfoVisibility = showAdditional;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MainWeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new MainWeatherHolder(layoutInflater
                .inflate(R.layout.weather_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainWeatherHolder mainWeatherHolder, int i) {
        String city = cities.get(i);
        String dayText = String.format(dayTemplate, Helpers.getDayOfWeek(), Helpers.getPartOfDay(mainWeatherHolder.itemView.getContext()));
        mainWeatherHolder.binds(city, dayText, Helpers.getFutureWeather(mainWeatherHolder.itemView.getContext()), additionalInfoVisibility);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class MainWeatherHolder extends RecyclerView.ViewHolder {
        final Random random = new Random();

        private TextView textViewLocationName;
        private LinearLayout linearLayoutAdditionalInfo;

        private TextView day;
        private GridView gridView;


        public MainWeatherHolder(@NonNull View itemView) {
            super(itemView);
            initElements(itemView);
            initWeatherValue(itemView);
            initHumidityValue(itemView);
            initPressureValue(itemView);
            initWindValue(itemView);
        }

        private void initElements(@NonNull View itemView) {
            textViewLocationName = itemView.findViewById(R.id.location_name);
            linearLayoutAdditionalInfo = itemView.findViewById(R.id.additional_info);
            day = itemView.findViewById(R.id.text_part_of_day);
            gridView = itemView.findViewById(R.id.grid_view_feature_weather);
        }

        private void initWeatherValue(@NonNull View itemView) {
            TextView weather = itemView.findViewById(R.id.text_weather_now);
            String weatherTemplate = itemView.getResources().getString(R.string.main_weather_now);
            int weatherValue = random.nextInt(100) - 50;
            String weatherText = String.format(weatherTemplate, String.valueOf(weatherValue));
            weather.setText(weatherText);
        }

        private void initHumidityValue(@NonNull View itemView) {
            TextView humidity = itemView.findViewById(R.id.text_humidity_now);
            String humidityTemplate = itemView.getResources().getString(R.string.main_humidity);
            int humidityValue = random.nextInt(100);
            String humidityText = String.format(humidityTemplate, String.valueOf(humidityValue));
            humidity.setText(humidityText);
        }

        private void initPressureValue(@NonNull View itemView) {
            TextView pressure = itemView.findViewById(R.id.text_pressure_now);
            String pressureTemplate = itemView.getResources().getString(R.string.main_pressure);
            int pressureValue = random.nextInt(100) + 680;
            String pressureText = String.format(pressureTemplate, String.valueOf(pressureValue));
            pressure.setText(pressureText);
        }

        private void initWindValue(@NonNull View itemView) {
            TextView wind = itemView.findViewById(R.id.text_wind_now);
            String windTemplate = itemView.getResources().getString(R.string.main_wind);
            int windValue = random.nextInt(20);
            String[] winds = itemView.getResources().getStringArray(R.array.winds);
            int idx = random.nextInt(winds.length);
            String windText = String.format(windTemplate, String.valueOf(windValue), winds[idx]);
            wind.setText(windText);
        }

        public void binds(String cityName, String dayText, FutureWeather[] futureWeathers, boolean visibility) {
            textViewLocationName.setText(cityName);
            day.setText(dayText);
            FutureWeatherAdapter futureWeatherAdapter = new FutureWeatherAdapter(linearLayoutAdditionalInfo.getContext(), futureWeathers);
            gridView.setAdapter(futureWeatherAdapter);
            linearLayoutAdditionalInfo.setVisibility(visibility ? View.VISIBLE : View.GONE);
        }
    }
}
