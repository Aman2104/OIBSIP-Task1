package com.example.oibsip_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        // Populate the spinners with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertValue();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void convertValue() {
        String inputText = inputValue.getText().toString().trim();
        if (inputText.isEmpty()) {
            resultTextView.setText("Please enter a value to convert.");
            return;
        }

        double inputValue = Double.parseDouble(inputText);
        String unitFrom = spinnerFrom.getSelectedItem().toString();
        String unitTo = spinnerTo.getSelectedItem().toString();

        if (unitFrom.equals("Centimeters") && unitTo.equals("Meters")) {
            double result = inputValue / 100.0;
            resultTextView.setText(inputValue + " cm = " + result + " m");
        } else if (unitFrom.equals("Meters") && unitTo.equals("Centimeters")) {
            double result = inputValue * 100.0;
            resultTextView.setText(inputValue + " m = " + result + " cm");
        } else if (unitFrom.equals("Grams") && unitTo.equals("Kilograms")) {
            double result = inputValue / 1000.0;
            resultTextView.setText(inputValue + " g = " + result + " kg");
        } else if (unitFrom.equals("Kilograms") && unitTo.equals("Grams")) {
            double result = inputValue * 1000.0;
            resultTextView.setText(inputValue + " kg = " + result + " g");
        } else {
            resultTextView.setText("Invalid unit conversion.");
        }

    }
}