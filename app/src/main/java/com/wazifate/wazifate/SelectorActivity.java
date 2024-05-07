package com.wazifate.wazifate;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wazifate.wazifate.HomeScreen.HomeActivity;

public class SelectorActivity extends AppCompatActivity {

    CardView jobQuizzesCV, skillQuizzesCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selector);
        jobQuizzesCV = findViewById(R.id.jobQuizzesCV);
        skillQuizzesCV = findViewById(R.id.skillQuizzesCV);

        jobQuizzesCV.setOnClickListener(v -> {
            Intent i = new Intent(SelectorActivity.this, HomeActivity.class);
            i.putExtra("fragmentToShow", "home");
            startActivity(i);

        });

        skillQuizzesCV.setOnClickListener(v -> {
            Intent i = new Intent(SelectorActivity.this, HomeActivity.class);
            i.putExtra("fragmentToShow", "quiz");
            startActivity(i);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}