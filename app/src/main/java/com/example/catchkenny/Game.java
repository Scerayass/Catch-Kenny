package com.example.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {
    TextView mode;
    TextView Score;
    TextView time;
    TextView topScore;

    int scoreNumber;
    int delay;
    String modeString;
    SharedPreferences sharedPreferences;


    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedPreferences = this.getSharedPreferences("com.example.catchkenny", Context.MODE_PRIVATE);


        Intent intent = getIntent();
        delay = intent.getIntExtra("delay",0);

        modeString = intent.getStringExtra("mode");
        int maxScore = sharedPreferences.getInt(modeString,0);


        Score = findViewById(R.id.textView5);
        time = findViewById(R.id.textView4);
        mode = findViewById(R.id.textView);
        mode.setText("Mode: "+modeString);

        topScore = findViewById(R.id.textView3);
        topScore.setText("Top Score: "+maxScore);
        
        imageView = findViewById(R.id.imageView2);
        imageView2 = findViewById(R.id.imageView3);
        imageView3 = findViewById(R.id.imageView4);
        imageView4 = findViewById(R.id.imageView5);
        imageView5 = findViewById(R.id.imageView6);
        imageView6 = findViewById(R.id.imageView7);
        imageView7 = findViewById(R.id.imageView8);
        imageView8 = findViewById(R.id.imageView9);
        imageView9 = findViewById(R.id.imageView10);

        imageViews = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        countTimer();
    }


    public void increaseScore(View view){
        scoreNumber++;
        int topScoreNumber = sharedPreferences.getInt(modeString,0);
        if(scoreNumber > topScoreNumber ){
            topScore.setText("Top score: " +scoreNumber);
        }
        Score.setText("Score: "+scoreNumber);
    }

    private void countTimer(){


        new CountDownTimer(30000,delay){

            @Override
            public void onTick(long l) {
                for(ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                for(ImageView image : imageViews){
                    time.setText("Time is over");
                    image.setVisibility(View.INVISIBLE);
                }

            }
        }.start();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText("Time:" + l/1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(Game.this);
                int topScoreNumber = sharedPreferences.getInt(modeString,0);
                if(scoreNumber > topScoreNumber){
                    //topScore.setText("Top Score: " + scoreNumber);
                    sharedPreferences.edit().putInt(modeString,scoreNumber).apply();
                }

                alert.setTitle("Congrua!");
                alert.setMessage("Your score is:" + scoreNumber +"\nDo you wanna play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Game.this,MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        }.start();

    }
}