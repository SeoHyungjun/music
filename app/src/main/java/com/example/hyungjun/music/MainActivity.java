package com.example.hyungjun.music;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img1, img2, img3, img4;
    BitmapDrawable bitmap;
    TextView l1_music, l2_music, l3_music, l4_music;
    TextView l1_singer, l2_singer, l3_singer, l4_singer;


    LinearLayout list1, list2, list3, list4;
    LinearLayout wrap_list1, wrap_list2, wrap_list3, wrap_list4;

    ImageView delete1, delete2, delete3, delete4;
    ImageView favor1, favor2, favor3, favor4;

    int favor1_lock = 0;
    int l1_index=0;
    int l2_index=1;
    int l3_index=2;
    int l4_index=3;

    //리스트 드래그 시 움직인 거리 * move_multi 만큼 이동하도록
    float move_multi = (float)2.0;

    float down_x, init_x, move_x;
    float screen_width;
    int anim_num = 0;

    //SingleTon patton activity 저장
    private DataManager dataManager= DataManager.getInstance();




    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager.setActivity(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen_width = size.x;


        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);


        l1_music = findViewById(R.id.list1_music);
        l2_music = findViewById(R.id.list2_music);
        l3_music = findViewById(R.id.list3_music);
        l4_music = findViewById(R.id.list4_music);


        l1_singer = findViewById(R.id.list1_singer);
        l2_singer = findViewById(R.id.list2_singer);
        l3_singer = findViewById(R.id.list3_singer);
        l4_singer = findViewById(R.id.list4_singer);


        list1 = findViewById(R.id.list1);
        list2 = findViewById(R.id.list2);
        list3 = findViewById(R.id.list3);
        list4 = findViewById(R.id.list4);

        wrap_list1 = findViewById(R.id.wrap_list1);
        wrap_list2 = findViewById(R.id.wrap_list2);
        wrap_list3 = findViewById(R.id.wrap_list3);
        wrap_list4 = findViewById(R.id.wrap_list4);



        delete1 = findViewById(R.id.delete1);// 슬라이딩으로 보여질 레이아웃 객체 참조
        delete2 = findViewById(R.id.delete2);
        delete3 = findViewById(R.id.delete3);
        delete4 = findViewById(R.id.delete4);

        favor1 = findViewById(R.id.favor1);
        favor1.setZ(1);
        favor2 = findViewById(R.id.favor2);
        favor3 = findViewById(R.id.favor3);
        favor4 = findViewById(R.id.favor4);


        Resources res = getResources();

        new ServerConn().execute();// list 받아오기

//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//        img1.setImageDrawable(bitmap);
//        l1_music.setText(dataManager.music_data.get(0).title);
//        l1_singer.setText(dataManager.music_data.get(0).singer);
//
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i2);
//        img2.setImageDrawable(bitmap);
//        l2_music.setText(dataManager.music_data.get(1).title);
//        l2_singer.setText(dataManager.music_data.get(1).singer);
//
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i3);
//        img3.setImageDrawable(bitmap);
//        l3_music.setText(dataManager.music_data.get(2).title);
//        l3_singer.setText(dataManager.music_data.get(2).singer);
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i4);
//        img4.setImageDrawable(bitmap);
//        l4_music.setText(dataManager.music_data.get(3).title);
//        l4_singer.setText(dataManager.music_data.get(3).singer);
//bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//        img1.setImageDrawable(bitmap);
//        l1_music.setText("What is Love?11111111");
//        l1_singer.setText("TWICE (트와이스)");
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i2);
//        img2.setImageDrawable(bitmap);
//        l2_music.setText("데리러 가 (Good Evening)");
//        l2_singer.setText("SHINee (샤이니)");
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i3);
//        img3.setImageDrawable(bitmap);
//        l3_music.setText("여행");
//        l3_singer.setText("볼빨간사춘기");
//
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i4);
//        img4.setImageDrawable(bitmap);
//        l4_music.setText("사랑을 했다 (LOVE SCENARIO)");
//        l4_singer.setText("iKON");


        wrap_list1.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    down_x = motionEvent.getX();
                    init_x = down_x;
                }

                if (action == MotionEvent.ACTION_MOVE) {
                    move_x = motionEvent.getX();


                    //터치 후 왼쪽으로 드래그
                    if(move_x < down_x) {
                        delete1.setVisibility(View.VISIBLE);
                        favor1.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if (screen_width - ((down_x - move_x) * move_multi) >= 0 && screen_width - ((down_x - move_x) * move_multi) <= screen_width) {
                            delete1.setTranslationX(screen_width - ((down_x - move_x) * move_multi));
                            list1.setTranslationX(-((down_x - move_x) * move_multi));
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if (screen_width - ((down_x - move_x) * move_multi) < 0) {
                            delete1.setTranslationX(0);
                            list1.setTranslationX(-screen_width);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            delete1.setTranslationX(0);
                            delete1.setVisibility(View.GONE);
                            list1.setTranslationX(0);
                        }
                        init_x = move_x;

                    }

                    //터치 후 오른쪽으로 드래그
                    else if (move_x > down_x) {
                        favor1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if ( (down_x - move_x) * -move_multi >= 0 && (down_x - move_x) * -move_multi <= screen_width) {
                            favor1.setTranslationX(-screen_width + (down_x - move_x) * -move_multi);
                            list1.setTranslationX((down_x - move_x) * -move_multi);
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if ( (down_x - move_x) * -move_multi < 0 ) {
                            favor1.setVisibility(View.GONE);
                            favor1.setTranslationX(0);
                            list1.setTranslationX(0);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            favor1.setTranslationX(0);
                            list1.setTranslationX(0);
                        }
                        init_x = move_x;

                    }
                }

                if (action == MotionEvent.ACTION_UP) {
                    anim_num = 0;

                    if( list1.getX() > 0 && favor1.getX() > -screen_width ) {
                        //드래그 후 땔때 favor가 화면의 반 이상 찼을 때 오른쪽으로 채워지고 추가
                        if ( favor1.getX() >= -1 * screen_width / 2.0 ) {
                            //println("if");
                            TranslateAnimation anim = new TranslateAnimation(favor1.getX(), 0, 0, 0);
                            anim.setDuration((int)favor1.getX() * -1 );
                            TranslateAnimation anim2 = new TranslateAnimation(list1.getX(), screen_width, 0, 0);
                            anim2.setDuration((int)favor1.getX() * -1 );
                            //리스트에 전체가 favor로 채워지고 난 뒤
                            //왼쪽 위 (어쩌면 favor와 delete 메뉴가 있으면 그곳으로)로 작아지면서 사라지는 애니메이션


                            favor1.startAnimation(anim);

                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Animation sanim = new AnimationUtils().loadAnimation(getApplicationContext(),R.anim.translate_left);
                                    favor1.startAnimation(sanim);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            list1.startAnimation(anim2);

                            list1.setTranslationX(0);
                            favor1.setTranslationX((float) 0.0);


                            change_list_text(1);
                            favor1.setVisibility(View.GONE);
                        }

                        //드래그 후 땔때 favor 화면의 반 미만일 때 다시 왼쪽으로 돌아감
                        else if ( favor1.getX() > -1 * screen_width && favor1.getX() < -1 * screen_width / 2.0 ){
                            TranslateAnimation anim = new TranslateAnimation(favor1.getX(), -screen_width, 0, 0);
                            anim.setDuration((int)list1.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list1.getX(), 0, 0, 0);
                            anim2.setDuration((int)list1.getX());


                            favor1.startAnimation(anim);
                            list1.startAnimation(anim2);

                            list1.setTranslationX(0);
                            favor1.setTranslationX(0);

                            favor1.setVisibility(View.GONE);
                        }
                    }
                    else if( list1.getX() < 0 && delete1.getX() < screen_width ) {
                        if ( delete1.getX() <= screen_width / 2.0) {
                            TranslateAnimation anim = new TranslateAnimation(delete1.getX(), 0, 0, 0);
                            anim.setDuration((int) delete1.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list1.getX(), -screen_width, 0, 0);
                            anim2.setDuration((int) delete1.getX());

                            delete1.startAnimation(anim);
                            list1.startAnimation(anim2);

                            list1.setTranslationX(0);
                            delete1.setTranslationX((float) 0.0);

                            change_list_text(1);
                            delete1.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 delete가 화면의 반 미만일 때 다시 오른쪽으로 돌아감
                        else if( delete1.getX() > screen_width / 2.0 && delete1.getX() < screen_width ){
                            TranslateAnimation anim = new TranslateAnimation(delete1.getX(), screen_width, 0, 0);
                            anim.setDuration((int) (screen_width - delete1.getX()));
                            TranslateAnimation anim2 = new TranslateAnimation(list1.getX(), 0, 0, 0);
                            anim2.setDuration((int) (screen_width - delete1.getX()));


                            delete1.startAnimation(anim);
                            list1.startAnimation(anim2);

                            list1.setTranslationX(0);
                            delete1.setTranslationX(0);

                            delete1.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        });

        wrap_list2.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    down_x = motionEvent.getX();
                    init_x = down_x;
                }

                if (action == MotionEvent.ACTION_MOVE) {
                    move_x = motionEvent.getX();


                    //터치 후 왼쪽으로 드래그
                    if(move_x < down_x) {
                        delete2.setVisibility(View.VISIBLE);
                        favor2.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if (screen_width - ((down_x - move_x) * move_multi) >= 0 && screen_width - ((down_x - move_x) * move_multi) <= screen_width) {
                            delete2.setTranslationX(screen_width - ((down_x - move_x) * move_multi));
                            list2.setTranslationX(-((down_x - move_x) * move_multi));
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if (screen_width - ((down_x - move_x) * move_multi) < 0) {
                            delete2.setTranslationX(0);
                            list2.setTranslationX(-screen_width);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            delete2.setTranslationX(0);
                            delete2.setVisibility(View.GONE);
                            list2.setTranslationX(0);
                        }
                        init_x = move_x;

                    }

                    //터치 후 오른쪽으로 드래그
                    else if (move_x > down_x) {
                        favor2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if ( (down_x - move_x) * -move_multi >= 0 && (down_x - move_x) * -move_multi <= screen_width) {
                            favor2.setTranslationX(-screen_width + (down_x - move_x) * -move_multi);
                            list2.setTranslationX((down_x - move_x) * -move_multi);
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if ( (down_x - move_x) * -move_multi < 0 ) {
                            favor2.setVisibility(View.GONE);
                            favor2.setTranslationX(0);
                            list2.setTranslationX(0);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            favor2.setTranslationX(0);
                            list2.setTranslationX(0);
                        }
                        init_x = move_x;

                    }
                }

                if (action == MotionEvent.ACTION_UP) {
                    anim_num = 0;

                    if( list2.getX() > 0 && favor2.getX() > -screen_width ) {
                        //드래그 후 땔때 favor가 화면의 반 이상 찼을 때 오른쪽으로 채워지고 추가
                        if ( favor2.getX() >= -1 * screen_width / 2.0 ) {
                            //println("if");
                            TranslateAnimation anim = new TranslateAnimation(favor2.getX(), 0, 0, 0);
                            anim.setDuration((int)favor2.getX() * -1 );
                            TranslateAnimation anim2 = new TranslateAnimation(list2.getX(), screen_width, 0, 0);
                            anim2.setDuration((int)favor2.getX() * -1 );

                            favor2.startAnimation(anim);
                            list2.startAnimation(anim2);

                            list2.setTranslationX(0);
                            favor2.setTranslationX((float) 0.0);

                            change_list_text(2);
                            favor2.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 favor 화면의 반 미만일 때 다시 왼쪽으로 돌아감
                        else if ( favor2.getX() > -1 * screen_width && favor2.getX() < -1 * screen_width / 2.0 ){
                            TranslateAnimation anim = new TranslateAnimation(favor2.getX(), -screen_width, 0, 0);
                            anim.setDuration((int)list2.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list2.getX(), 0, 0, 0);
                            anim2.setDuration((int)list2.getX());


                            favor2.startAnimation(anim);
                            list2.startAnimation(anim2);

                            list2.setTranslationX(0);
                            favor2.setTranslationX(0);

                            favor2.setVisibility(View.GONE);
                        }
                    }
                    else if( list2.getX() < 0 && delete2.getX() < screen_width ) {
                        if ( delete2.getX() <= screen_width / 2.0) {
                            TranslateAnimation anim = new TranslateAnimation(delete2.getX(), 0, 0, 0);
                            anim.setDuration((int) delete2.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list2.getX(), -screen_width, 0, 0);
                            anim2.setDuration((int) delete2.getX());

                            delete2.startAnimation(anim);
                            list2.startAnimation(anim2);

                            list2.setTranslationX(0);
                            delete2.setTranslationX((float) 0.0);

                            change_list_text(2);
                            delete2.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 delete가 화면의 반 미만일 때 다시 오른쪽으로 돌아감
                        else if( delete2.getX() > screen_width / 2.0 && delete2.getX() < screen_width ){
                            TranslateAnimation anim = new TranslateAnimation(delete2.getX(), screen_width, 0, 0);
                            anim.setDuration((int) (screen_width - delete2.getX()));
                            TranslateAnimation anim2 = new TranslateAnimation(list2.getX(), 0, 0, 0);
                            anim2.setDuration((int) (screen_width - delete2.getX()));


                            delete2.startAnimation(anim);
                            list2.startAnimation(anim2);

                            list2.setTranslationX(0);
                            delete2.setTranslationX(0);

                            delete2.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        });

        wrap_list3.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    down_x = motionEvent.getX();
                    init_x = down_x;
                }

                if (action == MotionEvent.ACTION_MOVE) {
                    move_x = motionEvent.getX();


                    //터치 후 왼쪽으로 드래그
                    if(move_x < down_x) {
                        delete3.setVisibility(View.VISIBLE);
                        favor3.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if (screen_width - ((down_x - move_x) * move_multi) >= 0 && screen_width - ((down_x - move_x) * move_multi) <= screen_width) {
                            delete3.setTranslationX(screen_width - ((down_x - move_x) * move_multi));
                            list3.setTranslationX(-((down_x - move_x) * move_multi));
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if (screen_width - ((down_x - move_x) * move_multi) < 0) {
                            delete3.setTranslationX(0);
                            list3.setTranslationX(-screen_width);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            delete3.setTranslationX(0);
                            delete3.setVisibility(View.GONE);
                            list3.setTranslationX(0);
                        }
                        init_x = move_x;

                    }

                    //터치 후 오른쪽으로 드래그
                    else if (move_x > down_x) {
                        favor3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if ( (down_x - move_x) * -move_multi >= 0 && (down_x - move_x) * -move_multi <= screen_width) {
                            favor3.setTranslationX(-screen_width + (down_x - move_x) * -move_multi);
                            list3.setTranslationX((down_x - move_x) * -move_multi);
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if ( (down_x - move_x) * -move_multi < 0 ) {
                            favor3.setVisibility(View.GONE);
                            favor3.setTranslationX(0);
                            list3.setTranslationX(0);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            favor3.setTranslationX(0);
                            list3.setTranslationX(0);
                        }
                        init_x = move_x;

                    }
                }

                if (action == MotionEvent.ACTION_UP) {
                    anim_num = 0;

                    if( list3.getX() > 0 && favor3.getX() > -screen_width ) {
                        //드래그 후 땔때 favor가 화면의 반 이상 찼을 때 오른쪽으로 채워지고 추가
                        if ( favor3.getX() >= -1 * screen_width / 2.0 ) {
                            TranslateAnimation anim = new TranslateAnimation(favor3.getX(), 0, 0, 0);
                            anim.setDuration((int)favor3.getX() * -1 );
                            TranslateAnimation anim2 = new TranslateAnimation(list3.getX(), screen_width, 0, 0);
                            anim2.setDuration((int)favor3.getX() * -1 );

                            favor3.startAnimation(anim);
                            list3.startAnimation(anim2);

                            list3.setTranslationX(0);
                            favor3.setTranslationX((float) 0.0);

                            change_list_text(3);
                            favor3.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 favor 화면의 반 미만일 때 다시 왼쪽으로 돌아감
                        else if ( favor3.getX() > -1 * screen_width && favor3.getX() < -1 * screen_width / 2.0 ){
                            TranslateAnimation anim = new TranslateAnimation(favor3.getX(), -screen_width, 0, 0);
                            anim.setDuration((int)list3.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list3.getX(), 0, 0, 0);
                            anim2.setDuration((int)list3.getX());


                            favor3.startAnimation(anim);
                            list3.startAnimation(anim2);

                            list3.setTranslationX(0);
                            favor3.setTranslationX(0);

                            favor3.setVisibility(View.GONE);
                        }
                    }
                    else if( list3.getX() < 0 && delete3.getX() < screen_width ) {
                        if ( delete3.getX() <= screen_width / 2.0) {
                            TranslateAnimation anim = new TranslateAnimation(delete3.getX(), 0, 0, 0);
                            anim.setDuration((int) delete3.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list3.getX(), -screen_width, 0, 0);
                            anim2.setDuration((int) delete3.getX());

                            delete3.startAnimation(anim);
                            list3.startAnimation(anim2);

                            list3.setTranslationX(0);
                            delete3.setTranslationX((float) 0.0);

                            change_list_text(3);
                            delete3.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 delete가 화면의 반 미만일 때 다시 오른쪽으로 돌아감
                        else if( delete3.getX() > screen_width / 2.0 && delete3.getX() < screen_width ){
                            TranslateAnimation anim = new TranslateAnimation(delete3.getX(), screen_width, 0, 0);
                            anim.setDuration((int) (screen_width - delete3.getX()));
                            TranslateAnimation anim2 = new TranslateAnimation(list3.getX(), 0, 0, 0);
                            anim2.setDuration((int) (screen_width - delete3.getX()));


                            delete3.startAnimation(anim);
                            list3.startAnimation(anim2);

                            list3.setTranslationX(0);
                            delete3.setTranslationX(0);

                            delete3.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        });

        wrap_list4.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    down_x = motionEvent.getX();
                    init_x = down_x;
                }

                if (action == MotionEvent.ACTION_MOVE) {
                    move_x = motionEvent.getX();


                    //터치 후 왼쪽으로 드래그
                    if(move_x < down_x) {
                        delete4.setVisibility(View.VISIBLE);
                        favor4.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if (screen_width - ((down_x - move_x) * move_multi) >= 0 && screen_width - ((down_x - move_x) * move_multi) <= screen_width) {
                            delete4.setTranslationX(screen_width - ((down_x - move_x) * move_multi));
                            list4.setTranslationX(-((down_x - move_x) * move_multi));
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if (screen_width - ((down_x - move_x) * move_multi) < 0) {
                            delete4.setTranslationX(0);
                            list4.setTranslationX(-screen_width);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            delete4.setTranslationX(0);
                            delete4.setVisibility(View.GONE);
                            list4.setTranslationX(0);
                        }
                        init_x = move_x;

                    }

                    //터치 후 오른쪽으로 드래그
                    else if (move_x > down_x) {
                        favor4.setVisibility(View.VISIBLE);
                        delete4.setVisibility(View.GONE);

                        //터치 후 움직인 거리 * move_multi 가 0 ~ 화면의 width 사이일 때
                        //즉 터치에 의해 움직임
                        if ( (down_x - move_x) * -move_multi >= 0 && (down_x - move_x) * -move_multi <= screen_width) {
                            favor4.setTranslationX(-screen_width + (down_x - move_x) * -move_multi);
                            list4.setTranslationX((down_x - move_x) * -move_multi);
                        }

                        //화면 범위보다 적으므로 왼쪽으로 더 못가게 고정
                        else if ( (down_x - move_x) * -move_multi < 0 ) {
                            favor4.setVisibility(View.GONE);
                            favor4.setTranslationX(0);
                            list4.setTranslationX(0);
                        }

                        //화면 범위보다 크므로 오른쪽으로 더 못가게 고정
                        else {
                            favor4.setTranslationX(0);
                            list4.setTranslationX(0);
                        }
                        init_x = move_x;

                    }
                }

                if (action == MotionEvent.ACTION_UP) {
                    anim_num = 0;

                    if( list4.getX() > 0 && favor4.getX() > -screen_width ) {
                        //드래그 후 땔때 favor가 화면의 반 이상 찼을 때 오른쪽으로 채워지고 추가
                        if ( favor4.getX() >= -1 * screen_width / 2.0 ) {
                            TranslateAnimation anim = new TranslateAnimation(favor4.getX(), 0, 0, 0);
                            anim.setDuration((int)favor4.getX() * -1 );
                            TranslateAnimation anim2 = new TranslateAnimation(list4.getX(), screen_width, 0, 0);
                            anim2.setDuration((int)favor4.getX() * -1 );

                            favor4.startAnimation(anim);
                            list4.startAnimation(anim2);

                            list4.setTranslationX(0);
                            favor4.setTranslationX((float) 0.0);

                            change_list_text(4);
                            favor4.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 favor 화면의 반 미만일 때 다시 왼쪽으로 돌아감
                        else if ( favor4.getX() > -1 * screen_width && favor4.getX() < -1 * screen_width / 2.0 ){
                            TranslateAnimation anim = new TranslateAnimation(favor4.getX(), -screen_width, 0, 0);
                            anim.setDuration((int)list4.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list4.getX(), 0, 0, 0);
                            anim2.setDuration((int)list4.getX());


                            favor4.startAnimation(anim);
                            list4.startAnimation(anim2);

                            list4.setTranslationX(0);
                            favor4.setTranslationX(0);

                            favor4.setVisibility(View.GONE);
                        }
                    }
                    else if( list4.getX() < 0 && delete4.getX() < screen_width ) {
                        if ( delete4.getX() <= screen_width / 2.0) {
                            TranslateAnimation anim = new TranslateAnimation(delete4.getX(), 0, 0, 0);
                            anim.setDuration((int) delete4.getX());
                            TranslateAnimation anim2 = new TranslateAnimation(list4.getX(), -screen_width, 0, 0);
                            anim2.setDuration((int) delete4.getX());

                            delete4.startAnimation(anim);
                            list4.startAnimation(anim2);

                            list4.setTranslationX(0);
                            delete4.setTranslationX((float) 0.0);

                            change_list_text(4);
                            delete4.setVisibility(View.GONE);

                        }

                        //드래그 후 땔때 delete가 화면의 반 미만일 때 다시 오른쪽으로 돌아감
                        else if( delete4.getX() > screen_width / 2.0 && delete4.getX() < screen_width ){
                            TranslateAnimation anim = new TranslateAnimation(delete4.getX(), screen_width, 0, 0);
                            anim.setDuration((int) (screen_width - delete4.getX()));
                            TranslateAnimation anim2 = new TranslateAnimation(list4.getX(), 0, 0, 0);
                            anim2.setDuration((int) (screen_width - delete4.getX()));


                            delete4.startAnimation(anim);
                            list4.startAnimation(anim2);

                            list4.setTranslationX(0);
                            delete4.setTranslationX(0);

                            delete4.setVisibility(View.GONE);
                        }
                    }
                }
                return true;
            }
        });



    }


    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        public void onAnimationEnd(Animation animation) {

        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    //토스트 띄우기용 (삭제예정)
    public void println(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    //num ( 리스트1,2,3,4 ) 지정해서 이미지, 노래, 가수 변경
    public void change_list_text(int num){

        int m_count = ++dataManager.m_count;
        Music_Data music_data = dataManager.music_data.get(m_count);
        switch (num)
        {
            case 1:
                l1_music.setText(music_data.title);
                l1_singer.setText(music_data.singer);
                new ImageDownload_URL().execute(Integer.toString(num),music_data.itunes_artwork_url);
                l1_index = m_count;
                break;
            case 2:
                l2_music.setText(music_data.title);
                l2_singer.setText(music_data.singer);
                new ImageDownload_URL().execute(Integer.toString(num),music_data.itunes_artwork_url);
                l2_index = m_count;
                break;
            case 3:
                l3_music.setText(music_data.title);
                l3_singer.setText(music_data.singer);
                new ImageDownload_URL().execute(Integer.toString(num),music_data.itunes_artwork_url);
                l3_index = m_count;
                break;
            case 4:
                l4_music.setText(music_data.title);
                l4_singer.setText(music_data.singer);
                new ImageDownload_URL().execute(Integer.toString(num),music_data.itunes_artwork_url);
                l4_index = m_count;
                break;
        }
//        if (num == 1) {
//            //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//            //img1.setImageDrawable(bitmap);
//
////            l1_singer.setText(singer);
//        }
//        else if (num == 2) {
//            //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//            //img2.setImageDrawable(bitmap);
//
//
////            l2_music.setText(sing);
////            l2_singer.setText(singer);
//        }
//        else if (num == 3) {
//            //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//            //img2.setImageDrawable(bitmap);
//
//
////            l2_music.setText(sing);
////            l2_singer.setText(singer);
//        }
//        else if (num == 4) {
//            //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//            //img2.setImageDrawable(bitmap);
//
//
////            l2_music.setText(sing);
////            l2_singer.setText(singer);
//        }
//        else
//            return;
    }

}
