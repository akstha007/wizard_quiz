package com.hp.ashokkumarshrestha.wizard;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class SortingpatronusActivity extends AppCompatActivity {

    private int totalQNo = 10;
    private int currentQNo = 1;
    private TextView txtQno;
    LinearLayout layoutBackground;
    LinearLayout layoutButton;
    private int[] colorsBg;
    private int colorIndex;
    private int[] arrQno;
    private int rndIndex = 0;
    private String strPatronusValue="";

    //rearrange the array elements to that of values
    private String[][] optionList = {
            {"Shine","Glitter","Glow"},
            {"Leaf","Blade","Thorn"},
            {"Sun","Wind","Rain"},
            {"Seek","Protect","Serve"},
            {"Dream","Discover","Dance"},
            {"Make","Improve"},
            {"Bright","Shadow"},
            {"Sweet","Salt"},
            {"Warm","Cold"},
            {"Blood","Bone"},
            {"Lead","Save","Escape"},
            {"Play","Prowl","Preen"},
            {"Think","Feel","Sense"},
            {"Stone","Wood","Earth"},
            {"Over","Under","Around"},
            {"Forever","Sometimes"},
            {"Who","Why"},
            {"Free","Safe"},
            {"Together","Alone"},
            {"Lost","Found"},
            {"Hope","Trust","Love"},
            {"Black","White","Grey"},
            {"Mind","Heart","Spirit"},
            {"Comfort","Advise","Impress"},
            {"Watch","Listen","Touch"}
    };

    private int[][] optionValues = {
            {3,1,2},
            {1,2,3},
            {1,2,3},
            {3,1,2},
            {1,3,2},
            {1,2},
            {1,2},
            {1,2},
            {1,2},
            {2,1},
            {3,2,1},
            {3,2,1},
            {3,2,1},
            {3,2,1},
            {3,2,1},
            {1,2},
            {1,2},
            {2,1},
            {1,2},
            {2,1},
            {1,3,2},
            {1,2,3},
            {1,2,3},
            {2,1,3},
            {2,1,3}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortingpatronus);

        //generate Random Question
        Random rnd = new Random();
        rndIndex = rnd.nextInt(500)%5;
        setElementIds();
        addQuestions();
    }

    private void setElementIds() {
        txtQno = (TextView)findViewById(R.id.txtQno);

        layoutBackground = (LinearLayout) findViewById(R.id.activity_sortingpatronus);
        layoutButton = (LinearLayout)findViewById(R.id.layoutButton);
    }

    private void addQuestions() {
        txtQno.setText(currentQNo+"/"+totalQNo);

        colorsBg = this.getResources().getIntArray(R.array.array_quiz_bg);

        colorIndex = currentQNo%colorsBg.length;
        layoutBackground.setBackgroundColor(colorsBg[colorIndex]);
        addButtons();
        changeStatusBarColor();
    }

    private void addButtons() {
        final LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        //int qindex = arrQno[currentQNo-1];
        final int btnNo = optionList[rndIndex].length;

        //int[] btnOptionArray = getResources().getIntArray(R.array.btnIds);
        int[] colors = this.getResources().getIntArray(R.array.btn_colors);

        for(int i=1;i<=btnNo;i++){
            //add some padding line view
            TextView v = new TextView(this);
            v.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,5));
            v.setBackgroundColor(colorsBg[colorIndex]);
            layout.addView(v);

            Button btn = new Button(this);
            //this will set text to btn options

            btn.setText(optionList[rndIndex][i-1]);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,1f));
            btn.setTextColor(colors[1]);
            btn.setBackgroundColor(colors[0]);

            layout.addView(btn);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentQNo<totalQNo) {
                        layout.setVisibility(View.GONE);
                    }
                    setView(finalI);
                }
            });
        }

        layoutButton.addView(layout);
    }

    private void setView(int val) {
        //int qindex = arrQno[currentQNo-1];
        val = optionValues[rndIndex][val-1];
        strPatronusValue += val;
        //Toast.makeText(SortingpatronusActivity.this,"val:"+val+"rnd:"+rndIndex,Toast.LENGTH_SHORT).show();

        if(currentQNo>=totalQNo){
            PrefManager prefmanager = new PrefManager(this);
            prefmanager.setPatronusPoints(strPatronusValue);
            //Toast.makeText(SortingpatronusActivity.this,"total val:"+prefmanager.getPatronusPoints() + " str:"+strPatronusValue,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SortingpatronusActivity.this,ResultpatronusActivity.class));
            finish();
        }else{
            currentQNo++;
            rndIndex = rndIndex+5;
            if(rndIndex>=optionList.length){
                rndIndex--;
                rndIndex = rndIndex%optionList.length;
            }
            addQuestions();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //do nothing
    }

    private void changeStatusBarColor() {
        int[] colorsTitleBar = this.getResources().getIntArray(R.array.array_quiz_titlebar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorsTitleBar[colorIndex]);
        }
    }
}
