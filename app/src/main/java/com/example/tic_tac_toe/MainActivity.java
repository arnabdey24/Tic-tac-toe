package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer chalkSound,gameSound;
    int user=0;
    int[] value={2,2,2,2,2,2,2,2,2};
    int[][] winingPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active=true;
    ImageView gameLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chalkSound= MediaPlayer.create(this,R.raw.chalksound);
        gameSound= MediaPlayer.create(this,R.raw.gamesound);
        gameLine=null;
    }


    public void popClick(View view){

        chalkSound.start();

        ImageView imageView=(ImageView) view;
        int index=Integer.parseInt(view.getTag().toString());
        Log.i("pressed",String.valueOf(index));

        TextView winTextView=(TextView)findViewById(R.id.winTextView);


        if(value[index]==2 && active) {

            if (user == 0) {
                imageView.setImageResource(R.drawable.o);
                user = 1;
                value[index] = 0;
                winTextView.setText("Cross's moves...");

            } else {
                imageView.setImageResource(R.drawable.x);
                user = 0;
                value[index] = 1;
                winTextView.setText("Circle's moves...");
            }

            for (int[] winingPosition : winingPositions) {
                if (value[winingPosition[0]] == value[winingPosition[1]] &&
                        value[winingPosition[1]] == value[winingPosition[2]] &&
                        value[winingPosition[0]] != 2) {

                    gameSound.start();

                    if(winingPosition[0]==0 && winingPosition[2]==8)
                        gameLine=findViewById(R.id.zerotoeight);
                    else if(winingPosition[0]==2 && winingPosition[2]==6)
                        gameLine=findViewById(R.id.twotosix);
                    else if(winingPosition[0]==0 && winingPosition[2]==6)
                        gameLine=findViewById(R.id.zerotosix);
                    else if(winingPosition[0]==1 && winingPosition[2]==7)
                        gameLine=findViewById(R.id.oneToSeven);
                    else if(winingPosition[0]==2 && winingPosition[2]==8)
                        gameLine=findViewById(R.id.twotoeight);
                    else if(winingPosition[0]==0 && winingPosition[2]==2)
                        gameLine=findViewById(R.id.zerototwo);
                    else if(winingPosition[0]==3 && winingPosition[2]==5)
                        gameLine=findViewById(R.id.threetofive);
                    else
                        gameLine=findViewById(R.id.siztoeight);


                    gameLine.setVisibility(View.VISIBLE);
                    active=false;
                    String winCall="";

                    if (user == 1) {
                        winCall="Circle has win!!";
                        Toast.makeText(this, "Circle has win!!", Toast.LENGTH_LONG).show();
                    } else {
                        winCall="Cross has win!!";
                        Toast.makeText(this, "Cross has win!!", Toast.LENGTH_LONG).show();
                    }

                    if(active==false){

                        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                        winTextView.setText(winCall);

                        //playAgainButton.setVisibility(View.VISIBLE);

                    }

                }

            }
            if(value[0]!=2 && value[1]!=2 && value[2]!=2 && value[3]!=2 && value[4]!=2 && value[5]!=2 && value[6]!=2 &&
                    value[7]!=2 && value[8]!=2 && active){

                winTextView.setText("Draw");
            }



        }

    }

    public void playAgain(View view){

        TextView winTextView=(TextView)findViewById(R.id.winTextView);
        winTextView.setText("Circle's moves...");
        user=0;
        active=true;
        if(gameLine!=null)
           gameLine.setVisibility(View.INVISIBLE);

        for (int i = 0; i < value.length; i++) {
            value[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.GridLayout1);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView=(ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

    }
}
