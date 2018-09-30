package com.hp.ashokkumarshrestha.wizard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOption1, btnOption2, btnOption3, btnOption4;
    TextView qNos, questions;
    private PrefManager prefManager;
    private Integer[] arrQno;
    private int totalQuestions = 10;
    private int qnos = 0;

    private String arrayHouse[] = {"pointG", "pointS", "pointR", "pointH"};

    private String[] arrayQuestions = {"Dawn or dusk?",
            "Forest or river?",
            "Moon or stars?",
            "Black or White?",
            "Heads or Tails?",
            "Left or Right?",
            "Four boxes are placed before you. Which would you try and open?",
            "You and two friends need to cross a bridge guarded by a river troll who insists on fighting one of you before he will let all of you pass. Do you:",
            "Once every century, the Flutterby bush produces flowers that adapt their scent to attract the unwary. If it lured you, it would smell of:",
            "One of your house mates has cheated in a Hogwarts exam by using a Self-Spelling Quill. Now he has come top of the class in Charms, beating you into second place. Professor Flitwick is suspicious of what happened. He draws you to one side after his lesson and asks you whether or not your classmate used a forbidden quill. What do you do?",
            "Which of the following do you find most difficult to deal with?",
            "You enter an enchanted garden. What would you be most curious to examine first?",
            "Four goblets are placed before you. Which would you choose to drink?",
            "What kind of instrument most pleases your ear?",
            "Which of the following would you most hate people to call you?",
            "After you have died, what would you most like people to do when they hear your name?",
            "How would you like to be known to history?",
            "A Muggle confronts you and says that they are sure you are a witch or wizard. Do you:",
            "Which nightmare would frighten you most?",
            "If you were attending Hogwarts, which pet would you choose to take with you?",
            "Given the choice, would you rather invent a potion that would guarantee you:",
            "If you could have any power, which would you choose?",
            "Which road tempts you most?",
            "Late at night, walking alone down the street, you hear a peculiar cry that you believe to have a magical source. Do you:",
            "What are you most looking forward to learning at Hogwarts?",
            "Which of the following would you most like to study?",
            "Which would you rather be:"};

    private String[][] arrayOptions = {
            {"Dawn", "Dusk"},
            {"Forest", "River"},
            {"Moon", "Stars"},
            {"Black", "White"},
            {"Heads", "Tails"},
            {"Left", "Right"},
            {"Small pewter box", "Ornate golden casket", "Small tortoiseshell box", "Gleaming jet black box"},
            {"Attempt to confuse the troll", "Suggest drawing lots", "Suggest that all three fight", "Volunteer to fight"},
            {"Home", "A crackling log fire", "The sea", "Fresh parchment"},
            {"Tell Professor Flitwick to ask your classmate", "Tell Professor Flitwick the truth", "Lie and say you don’t know", "Don't wait and tell before the exam started"},
            {"Hunger", "Cold", "Loneliness", "Being ignored"},
            {"bubbling pool", "silver leafed tree", "statue of an old wizard", "fat red toadstools"},
            {"Foaming, frothing, silvery liquid", "Smooth, thick, richly purple drink", "Mysterious black liquid", "Golden liquid"},
            {"Violin", "Piano", "Trumpet", "Drum"},
            {"Cowardly", "Ordinary", "Selfish", "Ignorant"},
            {"Miss you, but smile", "Think with admiration of your achievements", "Ask for more stories about your adventures", "I don't care what people think of me after I'm dead"},
            {"Great", "Bold", "Good", "Wise"},
            {"Ask what makes them think so", "Agree, and walk away, leaving them to wonder whether you are bluffing?", "Tell them that you are worried about their mental health", "Agree, and ask whether they’d like a free sample of a jinx?"},
            {"Your friends nor your family have any idea who you are", "Standing on top of something very high", "Being forced to speak in such a silly voice", "An eye at the keyhole"},
            {"Cat", "Dragon", "Snowy owl", "Harlequin toad"},
            {"Love", "Power", "Glory", "Wisdom"},
            {"Power to change appearance and power to read minds", "Power to change the past, superhuman strength", "Superhuman strength, power to speak to animals", "Power of invisibility, power to change past"},
            {"The twisting, leaf-strewn path through woods", "The cobbled street lined with ancient buildings", "The wide, sunny, grassy lane", "The narrow, dark, lantern-lit alley"},
            {"Draw your wand and stand your ground", "Draw your wand and try to discover the source of the noise", "Proceed with caution", "Withdraw into the shadows to await developments"},
            {"Magical creatures, flying on broomstick", "Hexes and jinxes and Apparition and Disapparition", "Transfiguration and every area of magic", "Secrets about the castle, flying on a broomstick"},
            {"Vampires, trolls and merpeople", "Werewolves, centaurs and ghosts", "Trolls, merpeople and werewolves", "Goblins, centaurs and ghosts"},
            {"Imitated", "Liked", "Feared", "Praised"}
    };

    private int[][] arrayAnsValue = {
            {11,41,31,21},
            {11,41,31,21},
            {31,11,21,41},
            {11,31,21,41},
            {31,11,41,21},
            {31,11,21,41},
            {11,31,41,21},
            {31,41,21,11},
            {41,11,21,31},
            {11,31,41,21},
            {31,41,11,21},
            {21,31,11,41},
            {31,41,21,11},
            {21,31,41,11},
            {11,21,41,31},
            {41,31,11,21},
            {21,11,41,31},
            {31,11,41,21},
            {41,31,21,11},
            {21,11,31,41},
            {41,21,11,31},
            {31,21,41,11},
            {11,31,41,21},
            {21,11,41,31},
            {41,21,31,11},
            {21,11,41,31},
            {31,41,21,11},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        prefManager = new PrefManager(this);


        //init btn ids
        initIds();

        //generate random question no.
        genRanQuestion();

        //set question and its no.
        setQuestion();
    }

    private void setQuestion() {
        /*change bg color of activity_quiz layout*/
        int[] bgColors = this.getResources().getIntArray(R.array.array_quiz_bg);
        RelativeLayout rl;
        rl = (RelativeLayout) findViewById(R.id.activity_quiz);
        Random rnd = new Random();  /*generate random value to choose from color arrays*/
        int cVal = rnd.nextInt(bgColors.length);
        cVal += rnd.nextInt(bgColors.length);
        cVal %= bgColors.length;
        rl.setBackgroundColor(bgColors[cVal]);
        // making notification bar transparent
        changeStatusBarColor(cVal);

        /*get current qnos from prefManager*/
        //int qnos = prefManager.getQNos();

        if (qnos < 0 || qnos >= totalQuestions) {
            return;
        }

        int qval = qnos+1;
        qNos.setText(qval + "/" + totalQuestions);

        //generate random question no.
        int rndQuestion = arrQno[qnos];

        //check length of text and change font size
        if(arrayQuestions[rndQuestion].length()>160){
            questions.setTextSize(15);
        }else if(arrayQuestions[rndQuestion].length()>100){
            questions.setTextSize(18);
        }else{
            questions.setTextSize(26);
        }

        questions.setText(arrayQuestions[rndQuestion]);


        btnOption1.setText(arrayOptions[rndQuestion][0]);
        btnOption2.setText(arrayOptions[rndQuestion][1]);

        if (arrayOptions[rndQuestion].length > 2) {
            btnOption3.setVisibility(View.VISIBLE);
            btnOption4.setVisibility(View.VISIBLE);
            btnOption3.setText(arrayOptions[rndQuestion][2]);
            btnOption4.setText(arrayOptions[rndQuestion][3]);
        } else {
            btnOption3.setVisibility(View.GONE);
            btnOption4.setVisibility(View.GONE);
        }

    }

    private void initIds() {
        btnOption1 = (Button) findViewById(R.id.btnQuizOption1);
        btnOption2 = (Button) findViewById(R.id.btnQuizOption2);
        btnOption3 = (Button) findViewById(R.id.btnQuizOption3);
        btnOption4 = (Button) findViewById(R.id.btnQuizOption4);
        qNos = (TextView) findViewById(R.id.quizNos);
        questions = (TextView) findViewById(R.id.quizQuestion);

        btnOption1.setOnClickListener(this);
        btnOption2.setOnClickListener(this);
        btnOption3.setOnClickListener(this);
        btnOption4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //int qnos = prefManager.getQNos();
        int rndQuestion = arrQno[qnos];
        int col = 0;
        //assign values to houses based on answer
        switch (v.getId()) {
            case R.id.btnQuizOption1:
                col = 0;
                break;
            case R.id.btnQuizOption2:
                col = 1;
                break;
            case R.id.btnQuizOption3:
                col = 2;
                break;
            case R.id.btnQuizOption4:
                col = 3;
                break;
        }

        int val = arrayAnsValue[rndQuestion][col];
        int r = (int) val / 10 - 1; //getting ten's digit so divinding by 10
        int c = val % 10; //getting one's digit so modulus by 10
        c += prefManager.getPoints(arrayHouse[r]);
        prefManager.setPoints(arrayHouse[r], c);
        //Toast.makeText(this,"House: " + arrayHouse[r]+c,Toast.LENGTH_SHORT).show();

        //check if only two options were given
        if (arrayOptions[rndQuestion].length == 2) {
            val = arrayAnsValue[rndQuestion][col + 2];
            r = (int) val / 10 - 1; //getting ten's digit so divinding by 10
            c = val % 10; //getting one's digit so modulus by 10
            c += prefManager.getPoints(arrayHouse[r]);
            prefManager.setPoints(arrayHouse[r], c);
            //Toast.makeText(this,"House: " + arrayHouse[r]+c,Toast.LENGTH_SHORT).show();
        }

        //determine which activity to launch
        launchActivity();
    }

    private void launchActivity() {
        //int qnos = prefManager.getQNos();

        if (qnos >= 0 && qnos < totalQuestions-1) {
            qnos++;
            //prefManager.setQNos(qnos);

            //set question and its no.
            setQuestion();
        } else {
            startActivity(new Intent(QuizActivity.this, ResultActivity.class));
            finish();
        }
    }

    private void genRanQuestion() {
        arrQno = new Integer[arrayQuestions.length];
        for (int i = 0; i < arrQno.length; i++) {
            arrQno[i] = i;
        }
        Collections.shuffle(Arrays.asList(arrQno));
    }

    //change status bar color
    private void changeStatusBarColor(int colorIndex) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int[] colorsTitleBar = this.getResources().getIntArray(R.array.array_quiz_titlebar);
            window.setStatusBarColor(colorsTitleBar[colorIndex]);
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
