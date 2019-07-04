package com.example.betterweather;

import android.annotation.SuppressLint;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class Helpers {

    private static Random random = new Random();

    public static String getPartOfDay(Context context) {
        String[] partsOfDay = context.getResources().getStringArray(R.array.parts_of_day);
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int idx;
        if (hours > 6 && hours < 11)
            idx = 0;
        else if (hours >= 11 && hours <= 18)
            idx = 1;
        else if (hours > 18 && hours <= 23)
            idx = 2;
        else
            idx = 3;
        return partsOfDay[idx];
    }

    public static String getDayOfWeek() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
            return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(Calendar.getInstance());
    }

    public static FutureWeather[] getFutureWeather(Context context) {
        String weatherTemplate = context.getResources().getString(R.string.main_weather_now);
        FutureWeather[] weather = new FutureWeather[4];
        for (int i = 0; i < 4; i++) {
            int weatherValue = random.nextInt(100) - 50;
            String weatherText = String.format(weatherTemplate, String.valueOf(weatherValue));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i + 1);
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("d MMM");
            String formatted = format.format(calendar.getTime());
            weather[i] = new FutureWeather(weatherText, formatted, R.drawable.sun);
        }

        return weather;
    }
}
