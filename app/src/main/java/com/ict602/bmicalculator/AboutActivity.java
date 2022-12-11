package com.ict602.bmicalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

public class AboutActivity extends AppCompatActivity {
    Button backBtn;
    Button repoBtn;
    String htmlText = "<a href='https://github.com/fauzirentah/BMICalculator'>https://github.com/fauzirentah/BMICalculator</a>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backBtn = findViewById(R.id.back_btn);
        repoBtn = findViewById(R.id.repo_btn);

        TextView htmlToTextView = findViewById(R.id.about_copyright);
        htmlToTextView.setText(HtmlCompat.fromHtml(htmlText, 0));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        repoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRepo();
            }
        });

    }

    public void gotoRepo() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fauzirentah/BMICalculator"));
        startActivity(browserIntent);
    }

}
