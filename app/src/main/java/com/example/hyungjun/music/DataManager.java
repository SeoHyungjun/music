package com.example.hyungjun.music;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

public class DataManager {

    private static DataManager instance = null;
    private Activity activity;
    private Context context;
    public ArrayList<Music_Data> music_data;
    public int m_count;
    public String play_url;


    private DataManager(){

        //생성자앞에 private으로 선언하면서,

        //다른 클래스에서 new 키워드를 사용하여 인스턴스를 만들 수 있는 여지를 막음.
        music_data = new ArrayList<>();
        m_count =0;


    }

    public static DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    public Activity getActivity() {
        return activity;
    }

    public Context getContext() { return context; }

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

}



