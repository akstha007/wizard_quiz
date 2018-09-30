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

public class SortingwandActivity extends AppCompatActivity {

    private int totalQNo = 7;
    private int currentQNo = 1;
    private int wandPoints = 0;
    private TextView txtQno, txtQuestion;
    LinearLayout layoutBackground;
    LinearLayout layoutButton;
    private int[] colorsBg;

    private String[] questionList = {
            "What height would you say you are for your age?",
            "What would you say your eye colour is?",
            "Is your birthday odd or even?",
            "What would you say is your best quality?",
            "Which path would you take?",
            "Which would you say your fear the most?",
            "Which artifact would you chose?",
    };

    private String[][] optionList = {
        {"Small","Average","Tall"},
        {"Brown","Dark Brown","Black","Blue","Grey","Green","Hazel","Other"},
        {"An odd number","An even number"},
        {"Kindness","Originality","Imagination","Optimism","Intelligence","Determination","Resilience"},
        {"Towards the Sea","Towards the Forest","Towards the Castle"},
        {"Isolation","Darkness","Small Spaces","Fire","Heights"},
        {"Ornate Mirror","Silver Dagger","Glittering Jewel","Bound-up Scroll","Dusty Bottle","Old Black Glove","Golden Key"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortingwand);

        setElementIds();
        addQuestions();
    }

    private void setElementIds() {
        txtQno = (TextView)findViewById(R.id.txtQno);
        txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        layoutBackground = (LinearLayout) findViewById(R.id.activity_sortingwand);
        layoutButton = (LinearLayout)findViewById(R.id.layoutButton);

        /*//custom font
        Typeface custom_font = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "hp.ttf");

        txtQno.setTypeface(custom_font);
        txtQuestion.setTypeface(custom_font);*/
    }

    private void addQuestions() {
        txtQno.setText(currentQNo+"/"+totalQNo);
        txtQuestion.setText(questionList[currentQNo-1]);

        colorsBg = this.getResources().getIntArray(R.array.array_quiz_bg);
        layoutBackground.setBackgroundColor(colorsBg[currentQNo]);
        addButtons();
        changeStatusBarColor();
    }

    private void addButtons() {
        final LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        final int btnNo = optionList[currentQNo-1].length;

        int[] btnOptionArray = getResources().getIntArray(R.array.btnIds);
        int[] colors = this.getResources().getIntArray(R.array.btn_colors);

        for(int i=1;i<=btnNo;i++){
            //add some padding line view
            TextView v = new TextView(this);
            v.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,5));
            v.setBackgroundColor(colorsBg[currentQNo]);
            layout.addView(v);

            Button btn = new Button(this);
            //this will set text to btn options
            btn.setText(optionList[currentQNo-1][i-1]);
            btn.setId(btnOptionArray[i-1]);
            btn.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            btn.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            //btn.setPadding(10,5,10,5);
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
        wandPoints += val*Math.pow(10,totalQNo-currentQNo);

        if(currentQNo>=totalQNo){
            PrefManager prefmanager = new PrefManager(this);
            prefmanager.setWandPoints(wandPoints);
            startActivity(new Intent(SortingwandActivity.this,ResultwandActivity.class));
            finish();
        }else{
            currentQNo++;
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
            window.setStatusBarColor(colorsTitleBar[currentQNo]);
        }
    }
}
