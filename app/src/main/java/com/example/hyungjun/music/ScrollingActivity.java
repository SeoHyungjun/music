package com.example.hyungjun.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        textView = findViewById(R.id.scroll_text_like);

        new ServerConn(textView).execute("get_like_list","like_list");

    }
}
