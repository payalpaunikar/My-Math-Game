package com.example.mymathgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameAddActivity extends AppCompatActivity {

    TextView txtScore,txtLife,txtTime;
    TextView txtQue;

    EditText edtAns;

    Button btnOk,btnNext;

    Random random=new Random();
   int num1,num2;
   int correctAns;

  int userAns;

 int score=0;
  int life=3;


 CountDownTimer timer;


 long leftTime=60000;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_add);



        txtLife=findViewById(R.id.txtLife);
        txtScore=findViewById(R.id.txtScore);
        txtTime=findViewById(R.id.txtTime);

        txtQue=findViewById(R.id.txtQue);

        edtAns=findViewById(R.id.edtAns);

        btnOk=findViewById(R.id.btnOk);
        btnNext=findViewById(R.id.btnNext);

        gererateRandomNo();
        startTimer();
        btnNext.setVisibility(View.INVISIBLE);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edtAns.getText().toString().equals("")) {
                    btnOk.setClickable(false);

                    userAns = Integer.parseInt(edtAns.getText().toString());
                    if (userAns == correctAns) {
                        txtQue.setText("Your answer is correct");
                        score = score + 10;
                        Log.d("TAG", "onClick: "+score);
                       // Toast.makeText(GameAddActivity.this, score, Toast.LENGTH_SHORT).show();
                        txtScore.setText(String.valueOf(score));
                        pausedTimer();
                        btnNext.setVisibility(View.VISIBLE);

                    } else {
                        txtQue.setText("Sorry,Your answer is wrong");
                        life = life - 1;
                        txtLife.setText(String.valueOf(life));
                        pausedTimer();
                        btnNext.setVisibility(View.VISIBLE);


                    }


                  //  Toast.makeText(GameAddActivity.this, l, Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(GameAddActivity.this, "Please enter your answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOk.setClickable(true);
                edtAns.setText("");
                if (life<=0)
                {
                    Intent i= new Intent(getApplicationContext(),ResultActivity.class);
                    i.putExtra("key",score);
                    Log.d("TAG", "onIntent "+score);
                    startActivity(i);
                    finish();
                }
                else
                {
                    gererateRandomNo();
                    startTimer();
                    btnNext.setVisibility(View.INVISIBLE);
                }

            }
        });

    }


        public  void gererateRandomNo()
        {
            num1=random.nextInt(100);
            num2=random.nextInt(100);


            correctAns=num1+num2;

            txtQue.setText(num1+" + "+num2);

        }


        public void startTimer()
        {
            timer=new CountDownTimer(leftTime,1000) {
                @Override
                public void onTick(long l) {

                    long second = l/1000;
                    txtTime.setText(String.valueOf(second));

                }

                @Override
                public void onFinish() {
                  txtTime.setText("0");
                  txtQue.setText("Your time is over");
                  life=life-1;
                  txtLife.setText(String.valueOf(life));
                  btnNext.setVisibility(View.VISIBLE);

                }
            };
                timer.start();

        }

            private void pausedTimer()
            {

                timer.cancel();
            }






}