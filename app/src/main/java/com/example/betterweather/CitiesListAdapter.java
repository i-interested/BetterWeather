package com.example.betterweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CitiesListHolder> {
    private ArrayList<String> cities;
    private OnItemButtonClickListener listener;

    public CitiesListAdapter(ArrayList<String> cities) {
        super();
        this.cities = cities;
    }

    public void setButtonDeleteListener(OnItemButtonClickListener listener){
        this.listener = listener;
    }

    public void addCity(String city) {
        if (cities.contains(city))
            return;

        cities.add(city);
        notifyItemInserted(cities.size() - 1);
    }

    public void removeCity(int idx) {
        notifyItemRemoved(idx);
        cities.remove(idx);
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    @NonNull
    @Override
    public CitiesListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewItem = LayoutInflater.from(viewGroup
                .getContext())
                .inflate(R.layout.city_item, viewGroup, false);

        return new CitiesListHolder(viewItem,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesListHolder citiesListHolder, int i) {
        citiesListHolder.binds(cities.get(i));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CitiesListHolder extends RecyclerView.ViewHolder {

        private TextView cityName;
        private Button deleteButton;

        public CitiesListHolder(@NonNull View itemView, OnItemButtonClickListener listener) {
            super(itemView);
            cityName = itemView.findViewById(R.id.item_city_name);
            deleteButton = itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(c -> listener.onItemClick(itemView, getAdapterPosition()));
        }

        void binds(String city) {
            cityName.setText(city);
        }
    }

    public interface OnItemButtonClickListener {
        void onItemClick(View view, int position);
    }
}

