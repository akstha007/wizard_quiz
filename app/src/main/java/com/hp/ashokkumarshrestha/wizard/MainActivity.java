package com.hp.ashokkumarshrestha.wizard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSortingHat, btnSortingWand, btnSortingPatronus, btnInitHouse, btnInitWand, btnInitPatronus;
    private PrefManager prefManager;
    private RelativeLayout rlHouse, rlWand, rlPatronus;
    Button btnPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new PrefManager(this);

        initIds();

        displayValues();
    }

    private void displayValues() {
        int[] arrLogo = {R.drawable.ic_g, R.drawable.ic_s, R.drawable.ic_r, R.drawable.ic_h};
        int[] arrBanner = {R.drawable.ic_gg, R.drawable.ic_ss, R.drawable.ic_rr, R.drawable.ic_hh};
        String[] arrHouseName = {"Griffindor", "Slytherin", "RavenClaw", "Hufflepuff"};


        //custom font
        Typeface custom_font = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "hp.ttf");

        //House
        int houseIndex = prefManager.getHouseChoosen();
        if (houseIndex > 3 || houseIndex < 0) {
            //house not set
            //Toast.makeText(this, "House id not set", Toast.LENGTH_SHORT).show();
            btnInitHouse.setVisibility(View.VISIBLE);
            rlHouse.setVisibility(View.GONE);

        } else {
            ImageView imgHouse = (ImageView) findViewById(R.id.imgHouse);
            imgHouse.setImageResource(arrLogo[houseIndex]);

            ImageView imgBanner = (ImageView) findViewById(R.id.imgBanner);
            imgBanner.setImageResource(arrBanner[houseIndex]);

            TextView txtHouse = (TextView) findViewById(R.id.houseName);
            txtHouse.setText(arrHouseName[houseIndex]);
            txtHouse.setTypeface(custom_font);


            int matchPoints = prefManager.getMatchingPoint();
            TextView txtMatchPoints = (TextView) findViewById(R.id.houseMatchings);
            txtMatchPoints.setText("Matching : " + matchPoints + "%");
            txtMatchPoints.setTypeface(custom_font);

            int[] colors = this.getResources().getIntArray(R.array.array_house_color);
            /*LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);
            layout.setBackgroundColor(colors[houseIndex]);*/

            //rlHouse.setBackgroundColor(colors[houseIndex]);
            txtHouse.setTextColor(colors[houseIndex]);
            txtMatchPoints.setTextColor(colors[houseIndex]);
            rlHouse.setBackgroundColor(this.getResources().getColor(R.color.txtWhite));

            //change status color
            //changeStatusBarColor(houseIndex);

            btnInitHouse.setVisibility(View.GONE);
            rlHouse.setVisibility(View.VISIBLE);
        }


        //wand
        int wandid = prefManager.getWandImage();
        if (wandid == 1) {
            //wand image not set
            // Toast.makeText(this, "Wand Image not set", Toast.LENGTH_SHORT).show();
            btnInitWand.setVisibility(View.VISIBLE);
            rlWand.setVisibility(View.GONE);
        } else {
            ImageView imgWand = (ImageView) findViewById(R.id.imgWand);
            imgWand.setImageResource(wandid);

            btnInitWand.setVisibility(View.GONE);
            rlWand.setVisibility(View.VISIBLE);
        }

        //patronus
        String patname = prefManager.getPatronusName();
        if (patname == "pat") {
            //patronus name not set
            //Toast.makeText(this, "Patronus name not set", Toast.LENGTH_SHORT).show();
            btnInitPatronus.setVisibility(View.VISIBLE);
            rlPatronus.setVisibility(View.GONE);
        } else {
            TextView txtPatronus = (TextView) findViewById(R.id.patronusName);
            String str = "Patronus \n" + patname;
            txtPatronus.setText(str);
            txtPatronus.setTypeface(custom_font);

            btnInitPatronus.setVisibility(View.GONE);
            rlPatronus.setVisibility(View.VISIBLE);
        }

    }

    private void initIds() {
        btnSortingHat = (Button) findViewById(R.id.btnSortingHouse);
        btnSortingWand = (Button) findViewById(R.id.btnSortingWand);
        btnSortingPatronus = (Button) findViewById(R.id.btnSortingPatronus);
        btnInitHouse = (Button) findViewById(R.id.btnInitHome);
        btnInitWand = (Button) findViewById(R.id.btnInitWand);
        btnInitPatronus = (Button) findViewById(R.id.btnInitPatronus);
        btnPolicy = (Button) findViewById(R.id.btnPolicy);

        btnSortingHat.setOnClickListener(this);
        btnSortingWand.setOnClickListener(this);
        btnSortingPatronus.setOnClickListener(this);
        btnInitHouse.setOnClickListener(this);
        btnInitWand.setOnClickListener(this);
        btnInitPatronus.setOnClickListener(this);
        btnPolicy.setOnClickListener(this);

        rlHouse = (RelativeLayout) findViewById(R.id.relayoutHouse);
        rlWand = (RelativeLayout) findViewById(R.id.relayoutWand);
        rlPatronus = (RelativeLayout) findViewById(R.id.relayoutPatronus);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInitHome:
            case R.id.btnSortingHouse:
                //Toast.makeText(this, "Sorting Hat", Toast.LENGTH_SHORT).show();
                initValues();
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
                finish();
                break;
            case R.id.btnInitWand:
            case R.id.btnSortingWand:
                //Toast.makeText(this,"Sorting Wand",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SortingwandActivity.class));
                finish();
                break;
            case R.id.btnInitPatronus:
            case R.id.btnSortingPatronus:
                //Toast.makeText(this,"Sorting Patronus",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SortingpatronusActivity.class));
                finish();
                break;
            case R.id.btnPolicy:
                policy();
                break;
        }
    }

    public void policy(){
        Uri uri = Uri.parse("https://ctechbytes.blogspot.com/2018/09/wizard-quiz-privacy-policy.html");
        Intent goToPolicy = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goToPolicy);
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

    private void initValues() {
        prefManager = new PrefManager(this);
        prefManager.setPoints("pointG",0);
        prefManager.setPoints("pointS",0);
        prefManager.setPoints("pointR",0);
        prefManager.setPoints("pointH",0);
        //prefManager.setQNos(1);
    }

    //exit prompt
    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("HP Quiz");
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doExit();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        doExit();
    }
}
