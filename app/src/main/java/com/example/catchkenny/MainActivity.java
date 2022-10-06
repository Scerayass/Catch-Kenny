package com.example.catchkenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
        public void easy(View view){
        Intent intent = new Intent(getApplicationContext(),Game.class);
        intent.putExtra("mode","easy");
        intent.putExtra("delay",700);

        startActivity(intent);
    }

    public void medium(View view){
        Intent intent = new Intent(MainActivity.this,Game.class);
        intent.putExtra("mode","medium");
        intent.putExtra("delay",500);

        startActivity(intent);
    }
    public void hard(View view){
        Intent intent = new Intent(MainActivity.this,Game.class);
        intent.putExtra("mode","hard");
        intent.putExtra("delay",300);

        startActivity(intent);
    }
    public void death(View view){
        Intent intent = new Intent(MainActivity.this,Game.class);
        intent.putExtra("mode","death");
        intent.putExtra("delay",150);

        startActivity(intent);
    }
}