package com.example.betterweather;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private EditText editTextLocationName;
    private Switch showAdditionalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        InitViewItems();
    }

    private void InitViewItems() {
        editTextLocationName = findViewById(R.id.settings_location_name);
        showAdditionalInfo = findViewById(R.id.settings_show_additional_info);

        String location = getIntent().getStringExtra(Identifiers.LOCATION_NAME);
        if (location != null)
            editTextLocationName.setText(location);

        boolean showAdditional = getIntent().getBooleanExtra(Identifiers.ADDITIONAL_INFO, true);
        showAdditionalInfo.setChecked(showAdditional);
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(Identifiers.LOCATION_NAME, editTextLocationName.getText().toString());
        result.putExtra(Identifiers.ADDITIONAL_INFO, showAdditionalInfo.isChecked());
        setResult(Activity.RESULT_OK, result);
        super.onBackPressed();
    }
}
