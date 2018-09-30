package com.hp.ashokkumarshrestha.wizard;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultpatronusActivity extends AppCompatActivity {

    private TextView txtResult;
    private int houseId = 2;//check the house name
    private Button btnTryAgain, btnHome;
    private PrefManager prefManager;

    private String[][] resultList = {
            {"Badger","11111"},
            {"Tonkinese Cat","11112"},
            {"Otter","11113"},
            {"Doe","11121"},
            {"Lynx","11121"},
            {"Stag","11121"},
            {"Siberian Cat","11122"},
            {"Weasel","11123"},
            {"Stallion","11211"},
            {"Dolphin","11212"},
            {"Nebelung Cat","11213"},
            {"Bassett Hound","11221"},
            {"White Mare","11222"},
            {"Deerhound","11223"},
            {"Red Squirrel","11311"},
            {"Black and White Cat","11312"},
            {"Husky","11313"},
            {"Wild Boar","11321"},
            {"Chow Dog","11322"},
            {"Newfoundland","11323"},
            {"Swift","12111"},
            {"Bloodhound","12112"},
            {"Eagle Owl","12113"},
            {"Scops Owl","12113"},
            {"Robin","12121"},
            {"Ibizan Hound","12122"},
            {"Brown Owl","12123"},
            {"Little Owl","12123"},
            {"Tortoiseshell Cat","12211"},
            {"Manx Cat","12212"},
            {"Borzoi","12213"},
            {"Dun Mare","12221"},
            {"West Highland Terrier","12222"},
            {"Irish Wolfhound","12223"},
            {"Black Swan","12311"},
            {"Piebald Mare","12312"},
            {"Ocicat","12313"},
            {"Mole","12321"},
            {"Ginger Cat","12322"},
            {"Mastiff","12323"},
            {"Aardvark","21111"},
            {"Mink","21112"},
            {"Erumpent","21113"},
            {"Orca","21113"},
            {"Unicorn","21113"},
            {"Rattlesnake","21121"},
            {"Hedgehog","21122"},
            {"Abraxan Winged Horse","21123"},
            {"Buffalo","21123"},
            {"Granian Winged Horse","21123"},
            {"Grey Squirrel","21211"},
            {"Black Mare","21212"},
            {"Brown Hare","21213"},
            {"Hedgehog","21221"},
            {"Calico Cat","21222"},
            {"Salmon","21223"},
            {"Shrew","21311"},
            {"Kingfisher","21312"},
            {"Capuchin Monkey","21313"},
            {"Field Mouse","21321"},
            {"Swallow","21322"},
            {"Brown Bear","21323"},
            {"Orangutan","22111"},
            {"Mountain Hare","22112"},
            {"Occamy","22113"},
            {"Peacock","22113"},
            {"Runespoor","22113"},
            {"Black Mamba","22121"},
            {"Wood Mouse","22122"},
            {"Dragon","22123"},
            {"Fire-dwelling Salamander","22123"},
            {"King Cobra","22123"},
            {"Polecat","22211"},
            {"Fox Terrier","22212"},
            {"Grass Snake","22213"},
            {"Weasel","22221"},
            {"Greyhound","22222"},
            {"Dragonfly","22223"},
            {"Vole","22311"},
            {"Sparrow","22312"},
            {"Sphynx Cat","22313"},
            {"Magpie","22321"},
            {"Crow","22322"},
            {"Heron","22323"},
            {"Blackbird","31111"},
            {"Wildcat","31112"},
            {"Cheetah","31113"},
            {"Python","31113"},
            {"Tiger","31113"},
            {"Bat","31121"},
            {"Great Grey Owl","31121"},
            {"Mongrel Dog","31122"},
            {"Black Bear","31123"},
            {"Elephant","31123"},
            {"Polar Bear","31123"},
            {"Rhinoceros","31123"},
            {"Osprey","31211"},
            {"St. Bernard","31212"},
            {"Stoat","31213"},
            {"Marsh Harrier","31221"},
            {"Bay Mare","31222"},
            {"Pine Marten","31223"},
            {"Rottweiler","31311"},
            {"Fox","31312"},
            {"Nightjar","31313"},
            {"Wolf","31321"},
            {"Falcon","31322"},
            {"Adder","31323"},
            {"Raven","32111"},
            {"Snowy Owl","32111"},
            {"Chestnut Mare","32112"},
            {"Impala","32113"},
            {"Oryx","32113"},
            {"Vulture","32113"},
            {"Buzzard","32121"},
            {"Russian Blue Cat","32122"},
            {"Leopard","32123"},
            {"Lion","32123"},
            {"Seal","32123"},
            {"Shark","32123"},
            {"Sparrowhawk","32211"},
            {"Ragdoll Cat","32212"},
            {"Fox","32213"},
            {"White Swan","32221"},
            {"Beagle","32222"},
            {"Wild Rabbit","32223"},
            {"Rat","32311"},
            {"Hummingbird","32312"},
            {"Pheasant","32313"},
            {"Hippogriff","32321"},
            {"Hyena","32321"},
            {"Thestral","32321"},
            {"Goshawk","32322"},
            {"Eagle","32323"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpatronus);

        prefManager = new PrefManager(this);
        setIds();
        calculateResult();

    }

    private void setIds() {
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnTryAgain = (Button) findViewById(R.id.wandTryAgain);
        btnHome = (Button)findViewById(R.id.btnHome);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultpatronusActivity.this, SortingpatronusActivity.class));
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
        String patronusPoints = prefManager.getPatronusPoints();
        int val1 = Integer.parseInt(patronusPoints.substring(0,5));
        int val2 = Integer.parseInt(patronusPoints.substring(5,10));
        //Toast.makeText(ResultpatronusActivity.this,"str: "+ patronusPoints +"val1:"+val1+" val2: "+val2,Toast.LENGTH_SHORT).show();

        //patronusPoints = patronusPoints%100000;
        for (int i = 0; i < resultList.length; i++) {
            int val = Integer.parseInt(resultList[i][1]);
            //display wand details
            if (val >= val1 || val >= val2 || i == (resultList.length - 1)) {
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

        String strDesc = resultList[index][0];//.replace(",", "\n");
        txtResult.setText(strDesc);

        //store value in pref
        prefManager.setPatronusName(strDesc);

        //custom font
        Typeface custom_font = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "hp.ttf");
        txtResult.setTypeface(custom_font);
        //btnTryAgain.setTypeface(custom_font);

        TextView txtPatronusTitle = (TextView)findViewById(R.id.txtYourWand);
        txtPatronusTitle.setTypeface(custom_font);

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
        startActivity(new Intent(ResultpatronusActivity.this, MainActivity.class));
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
}
