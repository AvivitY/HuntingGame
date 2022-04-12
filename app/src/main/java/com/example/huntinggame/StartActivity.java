package com.example.huntinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    private Button start_BTN_start;
    private TextView start_LBL_score;
    private TextView start_LBL_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_BTN_start = findViewById(R.id.start_BTN_start);
        start_LBL_score = findViewById(R.id.start_LBL_score);
        start_LBL_title = findViewById(R.id.start_LBL_title);
        start_BTN_start.setOnClickListener(view -> startGame());

        int score = 0;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
                score= extras.getInt("score");
                start_LBL_title.setText("Game Over!");
            }
        }
        start_LBL_score.setText(String.valueOf(score));
    }

    private void startGame() {
        Intent game = new Intent(StartActivity.this,MainActivity.class);
        startActivity(game);
        finish();
    }
}