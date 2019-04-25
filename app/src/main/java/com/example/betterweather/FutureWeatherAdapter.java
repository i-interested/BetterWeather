package com.example.betterweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FutureWeatherAdapter extends BaseAdapter {

    private final Context context;
    private final FutureWeather[] futureWeathers;

    public FutureWeatherAdapter(Context context, FutureWeather[] futureWeathers) {
        this.context = context;
        this.futureWeathers = futureWeathers;
    }

    @Override
    public int getCount() {
        return futureWeathers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FutureWeather futureWeather = futureWeathers[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.linearlayout_weather, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.linearlayout_imageview_weather);
        final TextView nameTextView = convertView.findViewById(R.id.linearlayout_textview_day);
        final TextView authorTextView = convertView.findViewById(R.id.linearlayout_textview_temperature);

        imageView.setImageResource(futureWeather.getImageResource());
        nameTextView.setText(futureWeather.getTemperature());
        authorTextView.setText(futureWeather.getDay());

        return convertView;
    }
}
