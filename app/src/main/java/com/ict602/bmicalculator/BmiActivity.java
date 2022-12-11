package com.ict602.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {
    TextView bmiValue, bmiCategory, bmiTips;
    String category;
    String bmiValOutput;
    Button calculateAgainBtn;
    Button aboutBtn;
    String[] bmiTipsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        bmiValue = findViewById(R.id.bmi_value);
        bmiCategory = findViewById(R.id.bmi_category);
        bmiTips = findViewById(R.id.bmi_tips);
        bmiTipsArray = getResources().getStringArray(R.array.tips_array);
        calculateAgainBtn = findViewById(R.id.calculate_again_btn);
        aboutBtn = findViewById(R.id.about_btn);
        bmiValOutput = getIntent().getStringExtra("bmiVal");
        bmiValue.setText(bmiValOutput);
        findCategory();
        categoryTips();
        calculateAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAbout();
            }
        });
    }

    public void viewAbout() {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    private void categoryTips() {
        double result = Double.parseDouble(bmiValOutput);
        if(result < 18.5) {
            bmiTips.setText(bmiTipsArray[0]);
        }
        else if(result >= 18.5 && result < 25) {
            bmiTips.setText(bmiTipsArray[1]);
        }
        else if(result >= 25 && result < 30) {
            bmiTips.setText(bmiTipsArray[2]);
        }
        else if(result >= 30 && result < 35) {
            bmiTips.setText(bmiTipsArray[3]);
        }
        else if(result >= 35 && result < 40) {
            bmiTips.setText(bmiTipsArray[4]);
        }
        else {
            bmiTips.setText(bmiTipsArray[5]);
        }
    }
    private void findCategory() {
        double result = Double.parseDouble(bmiValOutput);
        if(result < 18.5) {
            category = "<b><font color=#0047AB>Underweight</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
        else if(result >=18.5 && result < 25) {
            category = "<b><font color=#097969>Normal Weight</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
        else if(result >=25 && result < 30) {
            category = "<b><font color=#D22B2B>Overweight</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
        else if(result >=30 && result < 35) {
            category = "<b><font color=#D22B2B>Moderately Obese</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
        else if(result >=35 && result < 40) {
            category = "<b><font color=#D22B2B>Severely Obese</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
        else {
            category = "<b><font color=#D22B2B>Very Severely Obese</font></b>";
            bmiCategory.setText(Html.fromHtml(category));
        }
    }
}
