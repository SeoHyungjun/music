package org.androidtown.ituens_api_search_test;

/**
 * Created by MIN on 2018-06-09.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by syseng on 2017-10-30.
 */

// 서버와 데이터 통신을 하는 클래스
// 구글링을 통해 가져다 쓴 클래스로써 정확하게 모든 구조를 알지 못하고 변경한 부분만 주석처리 했습니다.
public class ServerConn extends AsyncTask<String, Void, String> {
    Context context;
    View view;
    //AlertDialog alertDialog;


//    ServerConn()
//    {
//
//    }
//
//    ServerConn(Context ctx) {
//        context = ctx;
//    }
    ServerConn(View view)
    {
        this.view = view;
    }

    @Override
    protected String doInBackground(String... params) {
        //params를 통해서 type과 그에 맞는 정보들을 받음
        //받은 항목중 인덱스 0번이 type이므로 type 변수에 저장
        String type = params[0];
//        new Welfare_Data(params[1],params[2],params[3],params[4],params[5]);


        //만약 로그인 시
//            String login_url = "http://13.124.63.18/WELFARE/welfare_service.php";
            String login_url = "http://littlecold2.iptime.org:3000/process/randommusic";

//            String login_url = "http://52.78.20.5/here/login_json.php";
            //서버에 있는 로그인 php와 통신하기 위해서 경로 지정
            try {
                //로그인 시 id, pw만 입력값으로 필요하므로 각 값을 변수에 저장
                URL url = new URL(login_url);

                //데이터 통신을 하기위한 연결
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //url을 통해서 보낼 데이터를 id = 'id' & pw = 'pw' 식으로 만들어서 인코딩
//                String post_data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&" + URLEncoder.encode("user_pw", "UTF-8") + "=" + URLEncoder.encode(user_pw, "UTF-8");
//                String a= Jsonize( Integer.parseInt(params[0]) ,Integer.parseInt(params[1]),params[2]);
//                bufferedWriter.write(a);
//                Log.d("json",Jsonize( Integer.parseInt(params[0]) ,Integer.parseInt(params[1]),params[2]));
//                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result +=  line+"\n";
                    if(line.contains("<img"))
                    {

                            String file_URL = "http://13.124.63.18/WELFARE/detail/"+ line.substring (line.indexOf("D"),line.indexOf("D")+7);
//                        line.codePointAt(line.indexOf("D"));
                         Log.d("index_i", Integer.toString(line.indexOf("D")));
                         Log.d("index_it", file_URL);


//                        line.
//                        new ImageDownload(view).execute(file_URL,"cache",line.substring (line.indexOf("D"),line.indexOf("D")+7));
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("server",result);
                Gson gson = new Gson();

                ArrayList<Music_Data> music_data = gson.fromJson(result, new TypeToken<ArrayList<Music_Data>>() {}.getType()); // 서버에서 받은 메시지(다른 클라이언트의 이름,위치 메시지 리스트 등)를 JSON->Gosn-> ArrayList<Message>로 해서 저장
                Log.d("ddddd",music_data.get(0).title);


                return result;

            } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                switch (type)
//                {
//
//
//            case "save_service":
//
//                break;
//            case "find_service":
//
//
//                break;
//
//                // save_service
//
//        }// switch




//        //회원가입 시
//        if (type.equals("signup")) {
//            String signup_url = "http://13.124.63.18/here_is/signup_json.php";
//            try {
//                String id = params[1];
//                String pw = params[2];
//                String name = params[3];
//                String info = params[4];
//                String youtube = params[5];
//                String index = params[6];
//                URL url = new URL(signup_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//
//                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
//                        + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8") + "&"
//                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
//                        + URLEncoder.encode("info", "UTF-8") + "=" + URLEncoder.encode(info, "UTF-8") + "&"
//                        + URLEncoder.encode("youtube", "UTF-8") + "=" + URLEncoder.encode(youtube, "UTF-8") + "&"
//                        + URLEncoder.encode("index", "UTF-8") + "=" + URLEncoder.encode(index, "UTF-8");
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                String result = "";
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    result += line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //회원정보 수정 시
//        if (type.equals("edit_profile")) {
//            String signup_url = "http://13.124.63.18/here_is/edit_profile_json.php";
//            try {
//                String id = params[1];
//                String pw = params[2];
//                String name = params[3];
//                String info = params[4];
//                String youtube = params[5];
//                String index = params[6];
//                URL url = new URL(signup_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//
//                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
//                        + URLEncoder.encode("pw", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8") + "&"
//                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
//                        + URLEncoder.encode("info", "UTF-8") + "=" + URLEncoder.encode(info, "UTF-8") + "&"
//                        + URLEncoder.encode("youtube", "UTF-8") + "=" + URLEncoder.encode(youtube, "UTF-8") + "&"
//                        + URLEncoder.encode("index", "UTF-8") + "=" + URLEncoder.encode(index, "UTF-8");
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                String result = "";
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    result += line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");
        //super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();
        super.onPostExecute(result);
        TextView v = view.findViewById(R.id.text2);

        if(result==null)
        {
            v.setText("결과 없음");

        }
        else {

            v.setText(result);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public String Jsonize(int pk, int data, String data2) // 데이터 받아서 JSON화 하는 함수 Data -> Gson -> json
    {

//        String json = new Gson().toJson(new Music_Data( pk,data,data2)); //Data -> Gson -> json
        return " ";
//        return json;

    }


}
