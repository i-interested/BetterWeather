package com.example.betterweather;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class Helpers {
    public static String[] partsOfDay = {"Morning", "Day", "Evening", "Night"};
    public static String[] partsOfDayRu = {"Утро", "День", "Вечер", "Ночь"};

    public static Locale ruLocale = new Locale("ru", "RU");

    public static String getPartOfDay() {
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
        return Locale.getDefault().equals(ruLocale) ? partsOfDayRu[idx] : partsOfDay[idx];
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
}
