package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.ArrayList;

public class PeriodTrackerActivity extends AppCompatActivity {

    Button startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_tracker);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        initialiseSpinner();
        initDates();
    }

    private void initDates() {
        MaterialDatePicker.Builder sdBuilder = MaterialDatePicker.Builder.datePicker();
        sdBuilder.setTitleText("SELECT APPOINTMENT DATE");
        final MaterialDatePicker sdPicker = sdBuilder.build();
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdPicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });
        sdPicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                startDate.setText(sdPicker.getHeaderText());
            }
        });

        MaterialDatePicker.Builder edBuilder = MaterialDatePicker.Builder.datePicker();
        edBuilder.setTitleText("SELECT APPOINTMENT DATE");
        final MaterialDatePicker edPicker = edBuilder.build();
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });
        edPicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                endDate.setText(edPicker.getHeaderText());
            }
        });
    }

    private void initialiseSpinner() {

        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getMonthList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PeriodTrackerActivity.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);

    }

    private ArrayList<String> getMonthList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("January");
        customers.add("February");
        customers.add("March");
        customers.add("April");
        customers.add("May");
        customers.add("June");
        customers.add("July");
        customers.add("August");
        customers.add("September");
        customers.add("October");
        customers.add("November");
        customers.add("December");
        return customers;
    }
}