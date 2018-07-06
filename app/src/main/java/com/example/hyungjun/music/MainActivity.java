package com.example.hyungjun.music;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img1, img2, img3, img4, img5;
    BitmapDrawable bitmap;
    TextView l1_music, l2_music, l3_music, l4_music, l5_music;
    TextView l1_singer, l2_singer, l3_singer, l4_singer, l5_singer;
    LinearLayout list1, list2, list3, list4;

    ImageView page;
    Animation translateLeftAnim, translateLeftAnim2;
    Animation translateRightAnim, translateRightAnim2;
    boolean isPageOpen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);


        l1_music = (TextView) findViewById(R.id.list1_music);
        l2_music = (TextView) findViewById(R.id.list2_music);
        l3_music = (TextView) findViewById(R.id.list3_music);
        l4_music = (TextView) findViewById(R.id.list4_music);


        l1_singer = (TextView) findViewById(R.id.list1_singer);
        l2_singer = (TextView) findViewById(R.id.list2_singer);
        l3_singer = (TextView) findViewById(R.id.list3_singer);
        l4_singer = (TextView) findViewById(R.id.list4_singer);

        list1 = (LinearLayout) findViewById(R.id.list1);
        list2 = (LinearLayout) findViewById(R.id.list2);
        list3 = (LinearLayout) findViewById(R.id.list3);
        list4 = (LinearLayout) findViewById(R.id.list4);




        page = (ImageView) findViewById(R.id.page);// 슬라이딩으로 보여질 레이아웃 객체 참조
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateLeftAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_left_music);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        translateRightAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_right_music);
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);



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

    }

    public void onButton1Clicked(View v) {

        Toast toastView = Toast.makeText(this, "ss",
                Toast.LENGTH_LONG);
        toastView.show();

        if (isPageOpen) {
// 애니메이션 적용
            list3.startAnimation(translateRightAnim2);
            page.startAnimation(translateRightAnim);
        } else {
            list3.startAnimation(translateLeftAnim2);
            page.setVisibility(View.VISIBLE);
            page.startAnimation(translateLeftAnim);
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            } else {
                page.setVisibility(View.VISIBLE);
                isPageOpen = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }


}
