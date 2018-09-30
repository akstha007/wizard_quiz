package com.hp.ashokkumarshrestha.wizard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultwandActivity extends AppCompatActivity {

    private TextView txtResult;
    private int houseId = 2;//check the house name
    private Button btnTryAgain, btnHome;
    private PrefManager prefManager;

    private int[] wandImageList = {
            R.drawable.wand1,
            R.drawable.wand2,
            R.drawable.wand3,
            R.drawable.wand4,
            R.drawable.wand5,
            R.drawable.wand6,
            R.drawable.wand7,
            R.drawable.wand8,
            R.drawable.wand9,
            R.drawable.wand10,
            R.drawable.wand11,
            R.drawable.wand12,
            R.drawable.wand13,
            R.drawable.wand14,
            R.drawable.wand15,
            R.drawable.wand16,
            R.drawable.wand17,
            R.drawable.wand18
    };

    private String[][] resultList = {
            {"Hazel, Unicorn Core, 11 inches, Slightly Springy", "1013357", "13"},
            {"Laurel, Unicorn Core, 9 3/4 inches, Hard", "1015113", "14"},
            {"Maple, Unicorn core, 10 inches, Surprisingly swishy", "1023114", "4"},
            {"Laurel, Phoenix Feather, 10 inches, Unyielding", "1025134", "14"},
            {"Acacia, Dragon core, 10 1/4 inches, Quite Flexible", "1111111", "10"},
            {"Redwood, Phoenix Feather, 11 inches, Hard", "1115137", "9"},
            {"Yew, Phoenix Feather, 11 inches, Unbending", "1116227", "15"},
            {"Cypress, Phoenix Feather, 9 3/4 inches, Surprisingly Swishy", "1123123", "12"},
            {"Acacia, Dragon core, 12 3/4 inches, Brittle", "1212222", "10"},
            {"Larch, Dragon Core, 12 3/4 inches, Supple", "1213152", "4"},
            {"Sycamore, Unicorn core, 10 inches, Slightly Springy", "1213154", "1"},
            {"English Oak, Unicorn core, 11 inches, Slightly Springy", "1213247", "13"},
            {"Ash, Unicorn core, 9 1/2 inches, Hard", "1215116", "11"},
            {"Ash, Dragon core, 12 3/4 inches, Hard", "1215122", "11"},
            {"Ash, Unicorn core, 10 inches, Hard", "1215144", "11"},
            {"Black Walnut, Phoenix Feather, 10 inches, Hard", "1215334", "16"},
            {"Black Walnut, Unicorn core, 10 1/2 inches, Hard", "1215355", "16"},
            {"Ebony, Unicorn core, 11 inches, Reasonably Supple", "1216217", "18"},
            {"Acacia, Unicorn core, 10 inches, Quite Bendy", "1222114", "10"},
            {"Sycamore, Unicorn core, 10 inches, Surprisingly Swishy", "1223144", "1"},
            {"Ash, Dragon core, 12 3/4 inches, Unyielding", "1225122", "11"},
            {"Black Walnut, Unicorn core, 10 inches, Unyielding", "1225344", "16"},
            {"Larch, Unicorn Core, 9 3/4 inches, Quite Flexible", "1311253", "4"},
            {"Elder, Unicorn core, 10 inches, Hard", "1315354", "2"},
            {"Willow, Dragon core, 10 1/4 inches, Quite Flexible", "1321111", "6"},
            {"Red Oak, Unicorn core, 11 inches, slightly yielding", "1321117", "9"},
            {"Pear, Phoenix Feather, 10 3/4 inches, Slightly yielding", "1321134", "8"},
            {"Cypress, Unicorn core, 10 1/4 inches, Rigid", "1321251", "12"},
            {"Hawthorn, Unicorn core, 10 inches, Slightly yielding", "1321314", "3"},
            {"Pear, Unicorn core, 10 1/4 inches, Surprisingly Swishy", "1323241", "8"},
            {"Elder, Unicorn core, 11 inches, Unyielding", "1325317", "2"},
            {"Ash, Phoenix Feather, 9 3/4 inches, Unbending", "1326323", "11"},
            {"Walnut, Dragon core, 9 1/2 inches, Rigid", "1327356", "17"},
            {"Ash, Phoenix Feather, 10 inches, quite flexible", "1411234", "11"},
            {"Ash, Unicorn core, 9 1/2 inches, Quite flexible", "1411236", "11"},
            {"Spruce, Phoenix Feather, 10 inches, Brittle", "1412334", "6"},
            {"Elm, Unicorn core, 10 inches, Pliant", "1414114", "17"},
            {"Vine, Dragon core, 9 1/2 inches, Hard", "1415126", "15"},
            {"Larch, Phoenix Feather, 11 inches, Hard", "1415237", "4"},
            {"Holly, Phoenix Feather, 11 inches, Supple", "1417237", "14"},
            {"Cypress, Unicorn core, 10 1/4 inches, Slightly Yielding", "1421121", "12"},
            {"Cypress, Phoenix Feather, 10 inches, Slightly Yielding", "1421124", "12"},
            {"Ebony, Unicorn core, 10 inches, Slightly Yielding", "1421344", "18"},
            {"Cedar, Dragon Core, 12 3/4 inches, Solid", "1424342", "7"},
            {"Blackthorn, Dragon Heartstring, 12 3/4 inches, Hard", "1515232", "12"},
            {"Beech, Unicorn core, 10 1/2 inches, Solid", "1524335", "11"},
            {"Elm, Phoenix Feather, 10 inches, Unyielding", "1525134", "17"},
            {"Elm, Unicorn core, 9 3/4 inches, Unyielding", "1525153", "17"},
            {"Larch, Phoenix Feather, 10 3/4 inches, Quite Flexible", "1611124", "4"},
            {"Poplar, Phoenix Feather, 10 inches, Hard", "1615324", "5"},
            {"Cypress, Unicorn core, 11 inches, Slightly Yielding", "1621217", "12"},
            {"Poplar, Unicorn core, 11 inches, unyielding", "1625357", "5"},
            {"Maple, Unicorn core, 12 1/4 inches, Slightly Springy", "2013147", "4"},
            {"Hazel, Phoenix Feather, 10 3/4 inches, Slightly yielding", "2013334", "13"},
            {"Maple, Unicorn core, 11 3/4 inches, Surprisingly swishy", "2023153", "4"},
            {"Laurel, Dragon Core, 12 1/2 inches, Unyielding", "2025132", "14"},
            {"Cedar, Dragon Core, 12 1/2 inches, Unbending", "2027352", "7"},
            {"Acacia, Dragon core, 11 1/2 inches, Quite Flexible", "2111111", "10"},
            {"Acacia, Unicorn core, 11 1/2 inches, Quite Flexible", "2111141", "10"},
            {"Beech, Unicorn core, 11 3/4 inches, Brittle", "2112343", "11"},
            {"Cypress, Unicorn core, 10 3/4 inches, Slightly Springy", "2113114", "12"},
            {"Vine, Phoenix Feather, 12 1/2 inches, Pliant", "2114112", "15"},
            {"Vine, Dragon core, 11 1/2 inches, Pliant", "2114131", "15"},
            {"Dogwood, Dragon core, 12 1/2 inches, Hard", "2115242", "13"},
            {"Dogwood, Dragon core, 12 1/2 inches, Hard", "2115252", "13"},
            {"Chestnut, Unicorn core, 12 1/4 inches, Hard", "2115317", "12"},
            {"Chestnut, Dragon core, 12 1/2 inches, Hard", "2115332", "12"},
            {"Fir, Dragon core, 11 1/2 inches, Reasonably Supple", "2116111", "2"},
            {"Maple, Dragon Core, 12 1/2 inches, Supple", "2117322", "4"},
            {"Elm, Dragon core, 12 1/2 inches, Solid", "2124342", "17"},
            {"Dogwood, Unicorn core, 10 3/4 inches, Unyielding", "2125254", "13"},
            {"Chestnut, Phoenix Feather, 10 3/4 inches, Unyielding", "2125324", "12"},
            {"Acacia, Dragon core, 12 1/2 inches, Brittle", "2212222", "10"},
            {"Acacia, Phoenix Feather, 12 1/2 inches, Brittle", "2213113", "10"},
            {"Sycamore, Dragon core, 12 1/2 inches, Slightly Springy", "2213142", "1"},
            {"English Oak, Phoenix, 12 1/4 inches, Slightly Springy", "2213227", "13"},
            {"Hornbeam, Phoenix Feather, 12 1/2 inches, Pliant", "2214312", "3"},
            {"Black Walnut, Phoenix Feather, 10 3/4 inches, Hard", "2215324", "16"},
            {"Aspen, Phoenix Feather, 12 1/2 inches, Reasonably supple", "2216312", "1"},
            {"Aspen, Unicorn core, 11 1/4 inches, Reasonably supple", "2216315", "1"},
            {"Pine, Phoenix Feather, 12 1/2 inches, Supple", "2217312", "5"},
            {"Pine, Dragon Heartstrings, 12 1/2 inches, Supple", "2217352", "5"},
            {"Silver Lime, Unicorn core, 10 3/4 inches, Slightly Yielding", "2221114", "15"},
            {"Silver Lime, Unicorn core, 12 1/4 inches, Slightly Yielding", "2221147", "15"},
            {"Silver Lime, Dragon core, 12 1/2 inches, Slightly Yielding", "2221152", "15"},
            {"Vine, Unicorn core, 10 3/4 inches, Slightly Yielding", "2221314", "15"},
            {"Acacia, Phoenix Feather, 10 3/4 inches, Quite Bendy", "2222124", "10"},
            {"Acacia, Unicorn core, 11 3/4 in, Quite bendy", "2222153", "10"},
            {"Sycamore, Phoenix Feather, 12 1/2 inches, Surprisingly Swishy", "2223112", "1"},
            {"Alder, Phoenix Feather, 10 3/4 inches, Surprisingly swishy", "2223334", "10"},
            {"Hazel, Phoenix Feather, 12 1/2 inches, Unyielding", "2225212", "13"},
            {"Black Walnut, Unicorn core, 12 1/4 inches, unyielding", "2225317", "16"},
            {"Ebony, Unicorn core, 10 3/4 inches, Unbending", "2226214", "18"},
            {"Pear, Unicorn core, 11 1/2 inches, Quite Flexible", "2311121", "8"},
            {"Willow, Phoenix Feather, 12 1/4 inches, Slightly yielding", "2311137", "6"},
            {"Cherry, Unicorn Core, 10 3/4 inches, Brittle", "2312154", "8"},
            {"Rowan, Unicorn Core, 10 3/4 inches, Slightly Springy", "2313114", "14"},
            {"Rowan, Unicorn Core, 10 3/4 inches, Slightly Springy", "2313144", "14"},
            {"Pear, Unicorn core, 10 3/4 inches, Slightly Springy", "2313244", "8"},
            {"Laurel, Dragon Core, 12 1/2 Inches, Slightly Springy", "2313332", "14"},
            {"Rowan, Unicorn Core, 11 1/2 inches, Pliant", "2314221", "14"},
            {"Ash, Phoenix Feather, 12 1/4 inches, Pliant", "2314327", "11"},
            {"Hawthorn, Unicorn core, 10 3/4 inches, Hard", "2315144", "3"},
            {"Hawthorn, Unicorn core, 12 1/4 inches, Hard", "2315147", "3"},
            {"Hawthorn, Unicorn core, 10 3/4 inches, Hard", "2315154", "3"},
            {"Cypress, Unicorn core, 12 1/4 inches, Hard", "2315157", "12"},
            {"Spruce, Unicorn core, 10 3/4 inches, Hard", "2315214", "6"},
            {"Spruce, Unicorn core, 10 3/4 inches, Hard", "2315254", "6"},
            {"Elder, Unicorn core, 10 3/4 inches, Hard", "2315354", "2"},
            {"Elm, Dragon core, 11 1/2 inches, Supple", "2317111", "17"},
            {"Maple, Unicorn core, 10 3/4 inches, Supple", "2317144", "4"},
            {"Pear, Unicorn core, 12 1/4 inches, Slightly yielding", "2321147", "8"},
            {"Pear, Unicorn core, 10 3/4 inches, Slightly yielding", "2321154", "8"},
            {"Redwood, Unicorn Core, 11 3/4 inches, Slightly Yielding", "2321343", "9"},
            {"Redwood, Unicorn Core, 10 3/4 inches, Slightly Yielding", "2321354", "9"},
            {"Elm, Dragon core, 12 1/2 inches, Quite bendy", "2322152", "17"},
            {"Rowan, Unicorn Core, 10 3/4 inches, Surprisingly Springy", "2323154", "14"},
            {"Sycamore, Unicorn core, 10 3/4 inches, Unbending", "2326114", "1"},
            {"Fir, Dragon core, 12 1/2 inches, rigid", "2327242", "2"},
            {"Beech, Dragon core, 12 1/2 inches, Brittle", "2412132", "11"},
            {"Hornbeam, Unicorn core, 12 1/4 inches, Slightly Springy", "2413147", "3"},
            {"Hornbeam, Unicorn core, 10 3/4 inches, Slightly Springy", "2413154", "3"},
            {"Beech, Unicorn core, 12 1/4 inches, Pliant", "2414257", "11"},
            {"Walnut, Unicorn core, 10 3/4 inches, Reasonably Supple", "2416214", "17"},
            {"Hornbeam, Dragon Core, 12 1/2 inches, Surprisingly Swishy", "2423152", "3"},
            {"Apple, Phoenix Feather, 12 1/4 inches, Surprisingly swishy", "2423227", "11"},
            {"Vine, Unicorn core, 11 1/2 inches, Unyielding", "2425141", "15"},
            {"Vine, Unicorn core, 11 3/4 inches, Unyielding", "2425153", "15"},
            {"Holly, Unicorn Core, 11 1/2 inches, Rigid", "2427251", "14"},
            {"Holly, Dragon core, 12 1/2 inches, Rigid", "2427252", "14"},
            {"Elm, Phoenix Feather, 10 3/4 inches, Hard", "2515124", "17"},
            {"Fir, Unicorn core, 10 3/4 inches, Hard", "2515314", "2"},
            {"Red Oak, Phoenix Feather, 10 3/4 inches, Slightly Yielding", "2521224", "9"},
            {"Beech, Unicorn core, 10 3/4 inches, Solid", "2524344", "11"},
            {"Elm, Phoenix Feather, 10 3/4 inches, Unyielding", "2525124", "17"},
            {"Vine, Unicorn core, 11 1/4 inches, Rigid", "2527135", "15"},
            {"Cypress, Phoenix Feather, 14 1/2 inches, Reasonably Supple", "3016234", "12"},
            {"Pine, Phoenix Feather, 14 1/2 inches, Unyielding", "3025324", "5"},
            {"Maple, Phoenix Feather, 14 1/2 inches, Rigid", "3027114", "4"},
            {"Acacia, Dragon core, 14 1/4 inches, Quite Flexible", "3111111", "10"},
            {"Spruce, Phoenix Feather, 13 3/4 inches, Slightly Springy", "3113337", "6"},
            {"Apple, Dragon core, 13 1/4 inches, Pliant", "3114213", "11"},
            {"Dogwood, Dragon core, 13 inches, Hard", "3115242", "13"},
            {"Rowan, Phoenix Feather, 13 inches, Supple", "3117212", "14"},
            {"Ebony, Phoenix Feather, 11 3/4 inches, Reasonably Supple", "3117252", "18"},
            {"Acacia, Phoenix Feather, 14 1/2 inches, Quite bendy", "3123134", "10"},
            {"Yew, Dragon core, 14 inches, Unbending", "3126256", "15"},
            {"Alder, Unicorn, 14 1/2 inches, slightly springy", "3213344", "10"},
            {"Ash, Dragon core, 13 inches, Hard", "3215122", "11"},
            {"Ash, Phoenix Feather, 14 1/2 inches, Hard", "3215124", "11"},
            {"Black Walnut, Phoenix Feather, 14 1/2 inches, Hard", "3215324", "16"},
            {"Pine, Phoenix Feather, 14 1/2 inches, Hard", "3215324", "5"},
            {"Apple, Unicorn core, 14 inches, Supple", "3217216", "11"},
            {"Apple, Dragon core, 14 inches, Supple", "3217256", "11"},
            {"Acacia, Unicorn core, 13 1/2 inches, Quite bendy", "3222135", "10"},
            {"Sycamore, Dragon core, 13 inches, Surprisingly Swishy", "3223132", "1"},
            {"Laurel, Dragon Core, 14 1/4 inches, Solid", "3224131", "14"},
            {"Black Walnut, Dragon Core, 13 inches, Unyielding", "3225352", "16"},
            {"Ash, Unicorn core, 13 1/2 inches, Rigid", "3227157", "11"},
            {"Willow, Unicorn core, 14 inches, Slightly yielding", "3311116", "6"},
            {"Cherry, Phoenix Core, 14 1/2 inches, Brittle", "3312124", "8"},
            {"Cherry, Dragon Core, 13 inches, Brittle", "3312142", "8"},
            {"Alder, Phoenix Feather, 13 3/4 inches, Pliant", "3314217", "10"},
            {"Elder, Phoenix Feather, 14 1/2 inches, Hard", "3315324", "2"},
            {"Elder, Dragon core, 13 inches, Hard", "3315342", "2"},
            {"Beech, Dragon core, 13 inches, Hard", "3316342", "11"},
            {"Beech, Unicorn core, 14 1/2 inches, Surprisingly swishy", "3323214", "11"},
            {"Ebony, Unicorn core, 14 1/4 Inches, Surprisingly Swishy", "3323341", "18"},
            {"Ebony, Dragon core, 13 Inches, Surprisingly Swishy", "3323342", "18"},
            {"Alder, Phoenix Feather, 13 inches, Solid", "3324212", "10"},
            {"Hawthorn, Phoenix Feather, 14 1/2 inches, Unyielding", "3325134", "3"},
            {"Beech, Phoenix Feather, 13 3/4 inches, Unbending", "3326327", "11"},
            {"Vine, Unicorn core, 13 1/4 inches, Hard", "3415153", "15"},
            {"Beech, Unicorn core, 13 3/4 inches, Quite Bendy", "3422117", "11"},
            {"Maple, Unicorn core, 13 3/4 inches, Surprisingly swishy", "3423347", "4"},
            {"Hazel, Unicorn Core, 12 1/4 inches, Unyielding", "3425317", "13"},
            {"Maple, Unicorn core, 14 1/4 inches, Slightly Yielding", "3521321", "4"},
            {"Hawthorn, Unicorn core, 14 1/4 inches, Surprisingly swishy", "3523321", "3"},
            {"Hawthorn, Unicorn core, 14 1/4 inches, Surprisingly swishy", "3523341", "3"},
            {"Hawthorn, Dragon core, 13 inches, Surprisingly swishy", "3523342", "3"},
            {"Blackthorn, Unicorn Hair, 13 1/2 inches, Unyielding", "3525215", "12"},
            {"Blackthorn, Phoenix Feather, 14 1/2 inches, Unyielding", "3525234", "12"},
            {"Ebony, Unicorn core, 13 1/4 inches, Rigid", "3527253", "18"},
            {"Pine, Phoenix Feather, 13 1/4 inches, Supple", "3617133", "5"},
            {"Dogwood, Unicorn core, 13 1/4 inches, Supple", "3617253", "13"},
            {"Maple, Dragon Core, 13 inches, Unyielding", "3625142", "4"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultwand);

        prefManager = new PrefManager(this);
        setIds();
        calculateResult();

        //alertResult();
        /*if(checkPromptDialogue()) {
            showCustomDialogue();
        }*/
    }

    private void setIds() {
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnTryAgain = (Button) findViewById(R.id.wandTryAgain);
        btnHome = (Button)findViewById(R.id.btnHome);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open sorting wand activity
                startActivity(new Intent(ResultwandActivity.this, SortingwandActivity.class));
                finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void calculateResult() {

        //setBgColor();

        PrefManager prefManager = new PrefManager(this);
        int wandPoints = prefManager.getWandPoints();
        for (int i = 0; i < resultList.length; i++) {
            int val = Integer.parseInt(resultList[i][1]);
            //display wand details
            if (val >= wandPoints || i == (resultList.length - 1)) {
                setResultIntoLayout(i);
                break;
            }
        }
    }

    private void setResultIntoLayout(int index) {
        Log.d("index:", "" + index);
        if (index < 0 || index >= resultList.length) {
            index = 0;
        }

        String strDesc = resultList[index][0].replace(",", "\n");
        txtResult.setText(strDesc);

        //custom font
        Typeface custom_font = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "hp.ttf");
        txtResult.setTypeface(custom_font);

        TextView txtPatronusTitle = (TextView)findViewById(R.id.txtYourWand);
        txtPatronusTitle.setTypeface(custom_font);

        //find image index from array
        int i = Integer.parseInt(resultList[index][2]);
        i--;
        if (i < 0 || i >= wandImageList.length) {
            i = 0;
        }
        ImageView im = (ImageView) findViewById(R.id.resultWandImage);
        im.setImageResource(wandImageList[i]);

        //store image id to pref
        prefManager.setWandImage(wandImageList[i]);
    }

    private void setBgColor() {
        //houseId = prefManager.getHouseChoosen();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_resultwand);
        int[] colors = this.getResources().getIntArray(R.array.array_house_color);
        layout.setBackgroundColor(colors[4]);

        changeStatusBarColor();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(ResultwandActivity.this, MainActivity.class));
        finish();
    }

    //status bar color
    private void changeStatusBarColor() {
        int[] colorsTitleBar = this.getResources().getIntArray(R.array.array_house_color_title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorsTitleBar[4]);
        }
    }

    public boolean checkPromptDialogue() {
        return prefManager.getResultWandPrompt();
    }

    public void showCustomDialogue() {
        View checkBoxView = View.inflate(this, R.layout.checkbox, null);
        CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save to shared preferences
                if (isChecked) {
                    //Toast.makeText(MainActivity.this, "Sorting Patronus", Toast.LENGTH_SHORT).show();
                    prefManager.setResultWandPrompt(false);
                }else{
                    prefManager.setResultWandPrompt(true);
                }
            }
        });
        checkBox.setText("Do not show this again");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CONGRATULATIONS!");
        builder.setMessage("You are one step closer to become a Wizard. Let the Magic begin!")
                .setView(checkBoxView)
                .setCancelable(false)
                .setPositiveButton("TRY WAND", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alertPlaystore();
                    }
                })
                .setNegativeButton("GOT IT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    //display message for playstore lumos app
    public void alertPlaystore() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder.setMessage("Do you want to try your wand to cast a spell to turn on/off light?");
        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //disaper spell
            }
        });
        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //link to playstore
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.lumosmaximus.ashokkumarshrestha.lumosmaximus")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.lumosmaximus.ashokkumarshrestha.lumosmaximus")));
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
