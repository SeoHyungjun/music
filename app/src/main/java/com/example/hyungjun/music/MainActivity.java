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

    Animation translateLeftAnim, translateLeftAnim2;
    Animation translateRightAnim, translateRightAnim2;
    boolean isPageOpen = false;

    float down_x, up_x, startdown_x, init_x, move_x;
    float screen_width;
    int anim_num = 0;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        delete1 = findViewById(R.id.delete1);// 슬라이딩으로 보여질 레이아웃 객체 참조
        delete2 = findViewById(R.id.delete2);
        delete3 = findViewById(R.id.delete3);
        delete4 = findViewById(R.id.delete4);

        favor1 = findViewById(R.id.favor1);
        favor2 = findViewById(R.id.favor2);
        favor3 = findViewById(R.id.favor3);
        favor4 = findViewById(R.id.favor4);


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
        l1_music.setText("What is Love?11111111111111111111111");
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


        wrap_list1.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //detector.onTouchEvent(motionEvent);

                int action = motionEvent.getAction();


                if (action == MotionEvent.ACTION_DOWN) {

                    /*if(anim_num == 0) {
                        anim_num = 1;
                        startdown_x = motionEvent.getX();
                    }*/
                    down_x = motionEvent.getX();
                    init_x = down_x;
                    //println(down_x.toString());

                    //list1.setTranslationX(0- (startdown_x - down_x));
                    //list1.startAnimation(translateLeftAnim2);
                    //delete1.setVisibility(View.VISIBLE);
                    //delete1.startAnimation(translateLeftAnim);
                    //delete1.setTranslationX(down_x);
                    //startdown_x = 1000 - (startdown_x - down_x);
                }

                if (action == MotionEvent.ACTION_MOVE) {
                    move_x = motionEvent.getX();


                    if(move_x < down_x) {
                        //list1.startAnimation(translateLeftAnim2);
                        delete1.setVisibility(View.VISIBLE);
                        //delete1.startAnimation(translateLeftAnim);
                        if (screen_width - ((down_x - move_x) * 5) >= 0 && screen_width - ((down_x - move_x) * 5) <= screen_width) {
                            delete1.setTranslationX(screen_width - ((down_x - move_x) * 5));
                            list1.setTranslationX(-((down_x - move_x) * 5));
                        } else if (screen_width - ((down_x - move_x) * 5) < 0) {
                            delete1.setTranslationX(0);
                            //delete1.setVisibility(View.GONE);
                            list1.setTranslationX(-screen_width);
                        } else {
                            delete1.setTranslationX(0);
                            delete1.setVisibility(View.GONE);
                            list1.setTranslationX(0);
                        }
                        init_x = move_x;

                    }
                }

                if (action == MotionEvent.ACTION_UP) {
                    anim_num = 0;
                    //list1.setTranslationX((float)0);
                    //delete1.setTranslationX((float)1000);
                    if (delete1.getX() < screen_width / 2.0) {
                        //삭제
                        TranslateAnimation anim = new TranslateAnimation(delete1.getX(), 0, 0, 0);
                        anim.setDuration((int) delete1.getX());
                        TranslateAnimation anim2 = new TranslateAnimation(list1.getX(), -screen_width, 0, 0);
                        anim2.setDuration((int) delete1.getX());

                        delete1.startAnimation(anim);
                        list1.startAnimation(anim2);

                        list1.setTranslationX(0);
                        delete1.setTranslationX((float) 0.0);

                    } else {
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
                return true;
            }
        });

        list2.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //detector.onTouchEvent(motionEvent);

                int action = motionEvent.getAction();


                if (anim_num == 0) {
                    if (action == MotionEvent.ACTION_DOWN) {
                        down_x = motionEvent.getX();
                        up_x = (float) 0.0;
                    }

                    if (action == MotionEvent.ACTION_UP) {
                        up_x = motionEvent.getX();

                        //오른쪽으로 드래그 시
                        if (up_x > down_x + 200) {
                            println("right");
                            delete2.setVisibility(View.GONE);
                            list2.startAnimation(translateRightAnim2);
                            favor2.setVisibility(View.VISIBLE);
                            favor2.startAnimation(translateRightAnim);
                        } else if (up_x < down_x - 200) {
                            println("left");
                            favor2.setVisibility(View.GONE);
                            list2.startAnimation(translateLeftAnim2);
                            delete2.setVisibility(View.VISIBLE);
                            delete2.startAnimation(translateLeftAnim);
                        }
                        down_x = (float) 0.0;
                        up_x = (float) 0.0;
                    }
                }
                return true;
            }
        });

        list3.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //detector.onTouchEvent(motionEvent);

                int action = motionEvent.getAction();

                if (anim_num == 0) {
                    if (action == MotionEvent.ACTION_DOWN) {
                        down_x = motionEvent.getX();
                        up_x = (float) 0.0;
                    }

                    if (action == MotionEvent.ACTION_UP) {
                        up_x = motionEvent.getX();

                        //오른쪽으로 드래그 시
                        if (up_x > down_x + 200) {
                            println("right");
                            delete3.setVisibility(View.GONE);
                            list3.startAnimation(translateRightAnim2);
                            favor3.setVisibility(View.VISIBLE);
                            favor3.startAnimation(translateRightAnim);
                        } else if (up_x < down_x - 200) {
                            println("left");
                            favor3.setVisibility(View.GONE);
                            list3.startAnimation(translateLeftAnim2);
                            delete3.setVisibility(View.VISIBLE);
                            delete3.startAnimation(translateLeftAnim);
                        }
                        down_x = (float) 0.0;
                        up_x = (float) 0.0;
                    }
                }

                return true;
            }
        });

        list4.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //detector.onTouchEvent(motionEvent);

                int action = motionEvent.getAction();

                if (anim_num == 0) {
                    if (action == MotionEvent.ACTION_DOWN) {
                        down_x = motionEvent.getX();
                        up_x = (float) 0.0;
                    }

                    if (action == MotionEvent.ACTION_UP) {
                        up_x = motionEvent.getX();

                        //오른쪽으로 드래그 시
                        if (up_x > down_x + 200) {
                            println("right");
                            delete4.setVisibility(View.GONE);
                            list4.startAnimation(translateRightAnim2);
                            favor4.setVisibility(View.VISIBLE);
                            favor4.startAnimation(translateRightAnim);
                        } else if (up_x < down_x - 200) {
                            println("left " + up_x + " " + down_x);
                            favor4.setVisibility(View.GONE);
                            list4.startAnimation(translateLeftAnim2);
                            delete4.setVisibility(View.VISIBLE);
                            delete4.startAnimation(translateLeftAnim);
                        }
                        down_x = (float) 0.0;
                        up_x = (float) 0.0;
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


    //버튼 클릭 (삭제예정)
    public void onButton1Clicked(View v) {
        if (isPageOpen) {
            // 애니메이션 적용
            list1.startAnimation(translateRightAnim2);
            delete1.startAnimation(translateRightAnim);
        } else {
            list1.startAnimation(translateLeftAnim2);
            delete1.setVisibility(View.VISIBLE);
            delete1.startAnimation(translateLeftAnim);
        }
    }


}
