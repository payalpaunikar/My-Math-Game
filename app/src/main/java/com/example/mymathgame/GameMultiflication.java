package com.example.mymathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameMultiflication extends AppCompatActivity {
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

                }

                else
                {
                    Toast.makeText(GameMultiflication.this, "Please enter your answer", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(GameMultiflication.this, "Game is over", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(GameMultiflication.this,ResultActivity.class);
                    i.putExtra("key",score);
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
        num1=random.nextInt(50);
        num2=random.nextInt(50);

            correctAns = num1 * num2;

            txtQue.setText(num1+" * "+num2);



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
