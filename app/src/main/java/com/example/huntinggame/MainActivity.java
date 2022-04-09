package com.example.huntinggame;

import static com.example.huntinggame.GameManager.COLS;
import static com.example.huntinggame.GameManager.ROWS;
import static com.example.huntinggame.GameManager.DIRECTION;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView main_LBL_score;
    private ImageView hearts[] = new ImageView[3];
    private ImageView main_IMG_heart1,main_IMG_heart2,main_IMG_heart3;
    private ImageButton main_BTN_up,main_BTN_right,main_BTN_left,main_BTN_down;
    private ImageView main_IMG_1,main_IMG_2,main_IMG_3,main_IMG_4,main_IMG_5,main_IMG_6,main_IMG_7,main_IMG_8,main_IMG_9,main_IMG_10,
            main_IMG_11,main_IMG_12,main_IMG_13,main_IMG_14,main_IMG_15;
    private ImageView mat[][] = new ImageView[ROWS][COLS];
    private int score = 0;
    private CountDownTimer timer;
    private boolean isTimerRunning = false;
    GameManager game = new GameManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        //initialize mat/array
        hearts[0] = main_IMG_heart1;
        hearts[1] = main_IMG_heart2;
        hearts[2] = main_IMG_heart3;
        ImageView temp[][] = {{main_IMG_1,main_IMG_2,main_IMG_3},{main_IMG_4,main_IMG_5,main_IMG_6},{main_IMG_7,main_IMG_8,main_IMG_9},
                {main_IMG_10,main_IMG_11,main_IMG_12},{main_IMG_13,main_IMG_14,main_IMG_15}};
        mat = temp;
        //timer
        timer = new CountDownTimer(1000000,1000) {
            @Override
            public void onTick(long l) {
                everySecond();
            }
            @Override
            public void onFinish() {
            }
        };
        timer.start();
        isTimerRunning = true;
        //move according to buttons
        main_BTN_up.setOnClickListener(view -> game.setDirection(DIRECTION.UP));
        main_BTN_right.setOnClickListener(view -> game.setDirection(DIRECTION.RIGHT));
        main_BTN_left.setOnClickListener(view -> game.setDirection(DIRECTION.LEFT));
        main_BTN_down.setOnClickListener(view -> game.setDirection(DIRECTION.DOWN));
    }

    private void everySecond() {
        //start with moving the hunted character down default
        moveCharacter(game.getDirection());
        main_LBL_score.setText(String.valueOf(++score));
    }

    private void moveCharacter(DIRECTION direction) {
        mat[game.getRowHunted()][game.getColHunted()].setImageResource(0);
        mat[game.getRowHunter()][game.getColHunter()].setImageResource(0);
        game.moveCharacter(direction);
        mat[game.getRowHunted()][game.getColHunted()].setImageResource(R.drawable.mouse);
        mat[game.getRowHunter()][game.getColHunter()].setImageResource(R.drawable.cat);
        if(game.isCatch()){
            //game restart position
            mat[game.getRowHunted()][game.getColHunted()].setImageResource(0);
            mat[game.getRowHunter()][game.getColHunter()].setImageResource(0);
            game.startPosition();
            mat[game.getRowHunted()][game.getColHunted()].setImageResource(R.drawable.mouse);
            mat[game.getRowHunter()][game.getColHunter()].setImageResource(R.drawable.cat);
            hearts[game.getLives()].setVisibility(View.INVISIBLE);
            if (game.isGameOver()){
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                intent.putExtra("score",score);
                startActivity(intent);
            }
        }
    }

    private void findViews() {
        //score
        main_LBL_score = findViewById(R.id.main_LBL_score);
        //hearts
        main_IMG_heart1 = findViewById(R.id.main_IMG_heart1);
        main_IMG_heart2 = findViewById(R.id.main_IMG_heart2);
        main_IMG_heart3 = findViewById(R.id.main_IMG_heart3);
        //button
        main_BTN_up = findViewById(R.id.main_BTN_up);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_down = findViewById(R.id.main_BTN_down);
        //mat
        main_IMG_1 = findViewById(R.id.main_IMG_1);
        main_IMG_2 = findViewById(R.id.main_IMG_2);
        main_IMG_3 = findViewById(R.id.main_IMG_3);
        main_IMG_4 = findViewById(R.id.main_IMG_4);
        main_IMG_5 = findViewById(R.id.main_IMG_5);
        main_IMG_6 = findViewById(R.id.main_IMG_6);
        main_IMG_7 = findViewById(R.id.main_IMG_7);
        main_IMG_8 = findViewById(R.id.main_IMG_8);
        main_IMG_9 = findViewById(R.id.main_IMG_9);
        main_IMG_10 = findViewById(R.id.main_IMG_10);
        main_IMG_11 = findViewById(R.id.main_IMG_11);
        main_IMG_12 = findViewById(R.id.main_IMG_12);
        main_IMG_13 = findViewById(R.id.main_IMG_13);
        main_IMG_14 = findViewById(R.id.main_IMG_14);
        main_IMG_15 = findViewById(R.id.main_IMG_15);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ccc","destroy");
        timer.cancel();
        isTimerRunning = false;
        score=0;
    }

    @Override
    protected void onPause() {
        Log.d("ccc","pause");
        super.onPause();
        timer.cancel();
        isTimerRunning = false;
    }

    @Override
    protected void onResume() {
        Log.d("ccc","resume");
        super.onResume();
        if(!isTimerRunning){
            timer.start();
        }
    }
}