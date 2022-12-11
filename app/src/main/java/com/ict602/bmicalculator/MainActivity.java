package com.ict602.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView weightCardView;
    TextView weightCounterText, height_title_text;
    FloatingActionButton weightBtnInc;
    FloatingActionButton weightBtnDec;
    int weightCounter; // = 50;
    String countWeight;
    NumberPicker mPicker, cmPicker;
    int mValue, cmValue;
    Button calculateBtn;
    String heightValue;
    DecimalFormat decimalFormat;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_WEIGHT = "text_weight";
    public static final String TEXT_M = "text_m";
    public static final String TEXT_CM = "text_cm";

    private String savedWeight;
    private String savedM;
    private String savedCm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightCardView = findViewById(R.id.weight_cardView);
        weightCounterText = findViewById(R.id.weight_counter_text);
        weightBtnInc = findViewById(R.id.weight_btn_inc);
        weightBtnDec = findViewById(R.id.weight_btn_dec);
        mPicker = findViewById(R.id.m_picker);
        cmPicker = findViewById(R.id.cm_picker);
        height_title_text = findViewById(R.id.height_title_text);
        calculateBtn = findViewById(R.id.calculate_btn);
        counterInit();
        decimalFormat = new DecimalFormat(".#");
        weightCardView.setOnClickListener(this);
        weightBtnInc.setOnClickListener(this);
        weightBtnDec.setOnClickListener(this);

        mPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mValue = newVal;
                heightValueIs();
            }
        });

        cmPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                cmValue = newVal;
                heightValueIs();
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                calculateBmi();
            }
        });

        loadData();
        updateData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.weight_cardView:
                break;
            case R.id.weight_btn_inc:
                if(weightCounter < 700)
                    weightCounter++;
                countWeight = Integer.toString(weightCounter);
                weightCounterText.setText(countWeight);
                break;
            case R.id.weight_btn_dec:
                if(weightCounter > 0)
                    weightCounter--;
                countWeight = Integer.toString(weightCounter);
                weightCounterText.setText(countWeight);
                break;
        }
    }
    private void counterInit() {
        countWeight = Integer.toString(weightCounter);
        weightCounterText.setText(countWeight);
        mPicker.setMinValue(0);
        mPicker.setMaxValue(3);
        cmPicker.setMinValue(0);
        cmPicker.setMaxValue(99);
        //mPicker.setValue(0);
        //cmPicker.setValue(0);
        heightValueIs();
    }
    public void heightValueIs() {
        if(cmValue == 0) {
            heightValue = mValue + " m ";
            height_title_text.setText(heightValue);
        }
        else {
            heightValue = mValue + " m " + cmValue + " cm ";
            height_title_text.setText(heightValue);
        }
    }
    public void calculateBmi() {
        double heightInCentimetres = mValue * 100 + cmValue;
        double heightInMetres = heightInCentimetres / 100;
        double heightInMetreSq = heightInMetres * heightInMetres;
        double bmi = weightCounter / heightInMetreSq;
        String bmiValue = decimalFormat.format(bmi);
        Intent intent = new Intent(this,BmiActivity.class);
        intent.putExtra("bmiVal", bmiValue);
        startActivity(intent);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT_WEIGHT, countWeight.toString());
        editor.putString(TEXT_M, String.valueOf(mValue));
        editor.putString(TEXT_CM, String.valueOf(cmValue));

        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        savedWeight = sharedPreferences.getString(TEXT_WEIGHT, "");
        savedM = sharedPreferences.getString(TEXT_M, "");
        savedCm = sharedPreferences.getString(TEXT_CM, "");
    }
    public void updateData() {
        if(savedWeight != "") {
            weightCounter = Integer.parseInt(savedWeight);
        }
        else
            savedWeight = "0";

        weightCounterText.setText(savedWeight);

        if(savedM != "" || savedCm != "") {
            mPicker.setValue(Integer.parseInt(savedM));
            cmPicker.setValue(Integer.parseInt(savedCm));
        }
        else {
            savedM = "1";
            savedCm = "62";
        }

        height_title_text.setText(savedM + " m " + savedCm + " cm" );
    }
}