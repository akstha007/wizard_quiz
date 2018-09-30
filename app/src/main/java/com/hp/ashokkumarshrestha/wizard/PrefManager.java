package com.hp.ashokkumarshrestha.wizard;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "uchihaashuke-harrypotterquiz";
    private static final String WAND_POINTS = "wand-points";
    private static final String PATRONUS_POINTS = "patronus-points";
    private static final String HOUSE_NAME_CHOSED = "house-choosen";
    private static final String RESULT_WAND_PROMPT = "result-wandprompt";
    private static final String WAND_ID = "wand-id";
    private static final String PATRONUS_NAME = "patronus-name";
    private static final String MATCHING_POINTS = "matching-points";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setPoints(String house, int point) {
        editor.putInt(house, point);
        editor.commit();
    }

    public int getPoints(String house) {
        return pref.getInt(house, 0);
    }

    public void setWandPoints(int point) {
        editor.putInt(WAND_POINTS, point);
        editor.commit();
    }

    public int getWandPoints() {
        return pref.getInt(WAND_POINTS, 1111111);
    }

    public void setPatronusPoints(String point) {
        editor.putString(PATRONUS_POINTS, point);
        editor.commit();
    }

    public String getPatronusPoints() {
        return pref.getString(PATRONUS_POINTS, "11111");
    }

    //set house id: G-0, S-1, R-2, H-3
    public void setHouseChoosen(int point){
        editor.putInt(HOUSE_NAME_CHOSED, point);
        editor.commit();
    }

    public int getHouseChoosen(){
        return pref.getInt(HOUSE_NAME_CHOSED,4);
    }

    public void setResultWandPrompt(boolean val){
        editor.putBoolean(RESULT_WAND_PROMPT, val);
        editor.commit();
    }

    public boolean getResultWandPrompt(){
        return pref.getBoolean(RESULT_WAND_PROMPT,true);
    }

    public void setWandImage(int id){
        editor.putInt(WAND_ID, id);
        editor.commit();
    }

    public int getWandImage(){
        return pref.getInt(WAND_ID,1);
    }

    public void setPatronusName(String str){
        editor.putString(PATRONUS_NAME, str);
        editor.commit();
    }

    public String getPatronusName(){
        return pref.getString(PATRONUS_NAME,"pat");
    }

    public void setMatchingPoint(int val){
        editor.putInt(MATCHING_POINTS, val);
        editor.commit();
    }

    public int getMatchingPoint(){
        return pref.getInt(MATCHING_POINTS,0);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
