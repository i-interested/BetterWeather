package com.example.betterweather;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private EditText editTextLocationName;
    private Switch showAdditionalInfo;
    private RecyclerView recyclerView;
    private Button addButton;
    private CitiesListAdapter citiesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViewItems();
    }

    private void initViewItems() {
        findViews();
        initAdditionalInfo();
        initRecyclerView();
        initButton();
        initTextEdit();
    }

    private void findViews() {
        editTextLocationName = findViewById(R.id.settings_location_name);
        showAdditionalInfo = findViewById(R.id.settings_show_additional_info);
        recyclerView = findViewById(R.id.recycler_view_cities);
        addButton = findViewById(R.id.add_button);
        textInputLayout = findViewById(R.id.setting_location_name_layout);
    }

    private void initAdditionalInfo() {
        boolean showAdditional = getIntent().getBooleanExtra(Identifiers.ADDITIONAL_INFO, true);
        showAdditionalInfo.setChecked(showAdditional);
    }

    private void initRecyclerView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> cities = getIntent().getStringArrayListExtra(Identifiers.CITIES);

        citiesListAdapter = new CitiesListAdapter(cities);
        citiesListAdapter.setButtonDeleteListener((view, position) -> {
            if (cities.size() == 1) {
                Toast.makeText(view.getContext(), getResources().getText(R.string.on_last_city_delete), Toast.LENGTH_SHORT).show();
                return;
            }
            citiesListAdapter.removeCity(position);
        });
        recyclerView.setAdapter(citiesListAdapter);
    }

    private void initButton() {
        addButton.setOnClickListener(c -> {
            String text = editTextLocationName.getText().toString();
            if (text.isEmpty()) {
                textInputLayout.setError(getResources().getText(R.string.empty_city_field));
                return;
            }
            citiesListAdapter.addCity(text);
            editTextLocationName.setText("");
        });
    }

    private void initTextEdit() {
        editTextLocationName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textInputLayout.getError() != null)
                    textInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(Identifiers.CITIES, citiesListAdapter.getCities());
        result.putExtra(Identifiers.ADDITIONAL_INFO, showAdditionalInfo.isChecked());
        setResult(Activity.RESULT_OK, result);
        super.onBackPressed();
    }
}
