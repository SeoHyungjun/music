package com.example.hyungjun.music;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView img1, img2, img3, img4, img5;
    BitmapDrawable bitmap;
    TextView l1_music, l2_music, l3_music, l4_music, l5_music;
    TextView l1_singer, l2_singer, l3_singer, l4_singer, l5_singer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);


        l1_music = (TextView) findViewById(R.id.list1_music);
        l2_music = (TextView) findViewById(R.id.list2_music);
        l3_music = (TextView) findViewById(R.id.list3_music);
        l4_music = (TextView) findViewById(R.id.list4_music);
        l5_music = (TextView) findViewById(R.id.list5_music);


        l1_singer = (TextView) findViewById(R.id.list1_singer);
        l2_singer = (TextView) findViewById(R.id.list2_singer);
        l3_singer = (TextView) findViewById(R.id.list3_singer);
        l4_singer = (TextView) findViewById(R.id.list4_singer);
        l5_singer = (TextView) findViewById(R.id.list5_singer);


        Resources res = getResources();

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
        img1.setImageDrawable(bitmap);
        l1_music.setText("What is Love?");
        l1_singer.setText("TWICE (트와이스)");

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i2);
        img2.setImageDrawable(bitmap);
        l2_music.setText("데리러 가 (Good Evening)");
        l2_singer.setText("SHINee (샤이니)");

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i3);
        img3.setImageDrawable(bitmap);
        l3_music.setText("여행");
        l3_singer.setText("볼빨간사춘기");

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i4);
        img4.setImageDrawable(bitmap);
        l4_music.setText("사랑을 했다 (LOVE SCENARIO)");
        l4_singer.setText("iKON");

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i5);
        img5.setImageDrawable(bitmap);
        l5_music.setText("밤 (Time for the moon night)");
        l5_singer.setText("여자친구 (GFRIEND)");
    }

}
