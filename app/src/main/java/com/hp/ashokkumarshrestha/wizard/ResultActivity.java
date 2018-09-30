package com.hp.ashokkumarshrestha.wizard;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView txtHouse,txtMatch,txtChar1,txtChar2,txtChar3;
    private ImageView icHouse;
    private Button btnHome, btnTryAgain;
    private RelativeLayout lBody;
    private PrefManager prefManager;
    private int matchPoint = 0;

    private String[] mHouse = {"GRYFFINDOR","SLYTHERIN","RAVENCLAW","HUFFLEPUFF"};
    private int[] mHouseImage = {R.drawable.ic_g,R.drawable.ic_s,R.drawable.ic_r,R.drawable.ic_h};
    private String[][] mChar = {{"COURAGE", "BRAVERY", "DETERMINATION"},
            {"CUNNING","AMBITIOUS","RESOURCEFUL"},
            {"CLEVERNESS","WISDOM","CREATIVITY"},
            {"HARD WORK","PATIENCE","LOYALTY"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        getIds();

        //get house name/id
        int houseId = getHouseId();

        setHouse(houseId);

        //play sorted house
        playSound(houseId);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();
            }
        });
    }

    private void playSound(int houseId) {
        int[] arrSound = {R.raw.sound_g,R.raw.sound_s,R.raw.sound_r,R.raw.sound_h};
        MediaPlayer mp = MediaPlayer.create(ResultActivity.this, arrSound[houseId]);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.release();
                //Toast.makeText(ResultActivity.this,"Audio Completed",Toast.LENGTH_SHORT).show();
                ImageView iv = (ImageView)findViewById(R.id.resultHatImage);
                iv.setVisibility(View.GONE);

                LinearLayout ll = (LinearLayout)findViewById(R.id.layoutResult);
                ll.setVisibility(View.VISIBLE);
            }
        });
        mp.start();
    }

    private void setHouse(int houseId) {
        if(houseId>=0 && houseId<=3) {
            int[] colors = this.getResources().getIntArray(R.array.array_house_color);
            lBody.setBackgroundColor(colors[houseId]);

            // making notification bar transparent
            changeStatusBarColor(houseId);

            txtHouse.setText(mHouse[houseId]);
            txtMatch.setText("Matching: " + matchPoint + "%");
            txtChar1.setText(mChar[houseId][0]);
            txtChar2.setText(mChar[houseId][1]);
            txtChar3.setText(mChar[houseId][2]);

            txtHouse.setTextColor(colors[houseId]);
            txtMatch.setTextColor(colors[houseId]);

            icHouse.setImageResource(mHouseImage[houseId]);

            prefManager.setHouseChoosen(houseId);
            prefManager.setMatchingPoint(matchPoint);
        }
    }

    private int getHouseId() {
        //get house id by shared prefs
        prefManager = new PrefManager(this);
        int[] arrPoints = new int[4];
        arrPoints[0] = prefManager.getPoints("pointG");
        arrPoints[1] = prefManager.getPoints("pointS");
        arrPoints[2] = prefManager.getPoints("pointR");
        arrPoints[3] = prefManager.getPoints("pointH");

        int point = 0, val = 0, total = 0;
        for(int i = 0;i<4;i++){
            total += arrPoints[i];
            if(arrPoints[i]>point){
                val = i;
                point = arrPoints[i];
            }
        }

        point *= 100;
        float v = point/total;
        matchPoint = (int)v;
        return val;
    }

    private void getIds() {
        txtHouse = (TextView)findViewById(R.id.resultHouse);
        txtMatch = (TextView)findViewById(R.id.resultMatch);
        txtChar1 = (TextView)findViewById(R.id.resultCharOne);
        txtChar2 = (TextView)findViewById(R.id.resultCharTwo);
        txtChar3 = (TextView)findViewById(R.id.resultCharThree);
        icHouse = (ImageView)findViewById(R.id.resultLogo);
        btnHome = (Button)findViewById(R.id.btnHome);
        btnTryAgain = (Button)findViewById(R.id.btnTryAgain);
        lBody = (RelativeLayout) findViewById(R.id.resultBody);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this,MainActivity.class));
        finish();
    }

    //change status bar color
    private void changeStatusBarColor(int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int[] colorsStatus = this.getResources().getIntArray(R.array.array_house_color_title);
            window.setStatusBarColor(colorsStatus[index]);
        }
    }
}

