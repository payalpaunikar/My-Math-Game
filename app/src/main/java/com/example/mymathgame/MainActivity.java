package com.example.mymathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnAdd,btnSub,btnMul;
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd=findViewById(R.id.btnAdd);
        img=findViewById(R.id.img);
        btnMul=findViewById(R.id.btnMul);
        btnSub=findViewById(R.id.btnSub);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,GameAddActivity.class);
                Log.d("check", "onClick: ");
                startActivity(i);
                finish();
            }
        });


        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,GameSubtractionActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,GameMultiflication.class);
                startActivity(i);
                finish();
            }
        });


        Animation anim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);

        img.startAnimation(anim);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ");

    }
}