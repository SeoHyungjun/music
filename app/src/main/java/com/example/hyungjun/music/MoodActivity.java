package com.example.hyungjun.music;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MoodActivity extends AppCompatActivity {

    SharedPreferences userinfo;

    EditText editText;
    private DataManager dataManager= DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_mood);

        editText = findViewById(R.id.edit_text_id);


        SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        //전에 로그인 후 로그아웃을 안했을 경우 바로 MapActivity 실행
        if (userinfo != null) {
            //이전에 저장했던 유저정보에 login_ok라는 정보가 있다면, 바로 MapActivity에 저장 하면될듯
             editText.setText(userinfo.getString("ID", ""));

        }





    }
    protected void saveUserinfo(String mood) {
        SharedPreferences userinfo = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userinfo.edit();


        if(editText.getText().toString().equals("")) {
            println("아이디 적어라 ㅡㅡ 저장되니까");
            return;
        }
        if(userinfo!=null) {
            println("userinfo != null");
            editor.clear();
            editor.commit();
        }

        editor.putString("STATUS", "Login_OK");
        editor.putString("ID", editText.getText().toString());

        editor.commit();
        dataManager.ID= editText.getText().toString();
        dataManager.mood =mood;

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.putExtra(KEY_BEFORE_USER_DATA, before_data);
        startActivity(intent);
    }


    public void feel_happy(View view) {

        saveUserinfo("1");
    }

    public void feel_sad(View view) {
        saveUserinfo("2");

    }

    public void feel_angry(View view) {
        saveUserinfo("3");

    }

    public void feel_mung(View view) {
        saveUserinfo("4");

    }


    public void println(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}


