package com.example.mymathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class ResultActivity extends AppCompatActivity {
    TextView txtResultScore;
    Button btnPlay,btnExit;

    Intent intent;
    int score=0;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResultScore=findViewById(R.id.txtResultScore);
        btnExit=findViewById(R.id.btnExit);
        btnPlay=findViewById(R.id.btnPlay);






        intent =getIntent();
        score=intent.getIntExtra("key",0);
        txtResultScore.setText(String.valueOf(score));
        Log.d("TAG", "getIntent "+score);



            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i=new Intent(ResultActivity.this,MainActivity.class);
                    startActivity(i);

                }
            });

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   finish();
                }
            });


       mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.drum_sound);
        mediaPlayer.setLooping(true); // Set looping to true for infinite loop
        mediaPlayer.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        finish();
    }
}
