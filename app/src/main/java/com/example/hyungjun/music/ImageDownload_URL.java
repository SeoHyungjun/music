package com.example.hyungjun.music;

/**
 * Created by MIN on 2018-05-29.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by MIN on 2018-05-03.
 */

public class ImageDownload_URL extends AsyncTask<String, Void, String> {


//    private SharedPreferences userinfo;
//    private String ID;
    DataManager dataManager = DataManager.getInstance();
    Bitmap bitmap;
//    Context context;

//    ImageDownload(Context context)
//    {
//        this.context = context;
////        userinfo =  context.getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
////        this.ID ="";
//    }

    public ImageDownload_URL() {

    }

    // 리스트 번호, URL 받기
    @Override
    protected String doInBackground(String... params) {

        //다운로드 경로를 지정
//        String savePath = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;


//        fileName = String.valueOf(sdf.format(day));
            Log.d("dddd",params[0]);
            Log.d("dddd",params[1]);

            String img_url = params[1];
            try {
                URL imgUrl = new URL(img_url);
                //서버와 접속하는 클라이언트 객체 생성
                HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                conn.setDoInput(true); // 서버로 부터 응답 수신
                conn.connect();

                InputStream is = conn.getInputStream(); // InputStream 값 가져오기
                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환


                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return params[0];
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

//        View rootView = ((Activity)dataManager.getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
        ImageView v,v2;
        Activity root = dataManager.getActivity();
        switch (result)
        {
            case "0":
                v = root.findViewById(R.id.player);
                v2 = root.findViewById(R.id.player2);
//                bitmap= Bitmap.createScaledBitmap(bitmap ,v.getMaxWidth(),v.getMaxHeight(),true);
//                Bitmap s_bitmap = Bitmap.createScaledBitmap(bitmap ,v.getMaxWidth(),v.getMaxHeight(),true)
                v.setImageBitmap(Bitmap.createScaledBitmap(bitmap ,v.getWidth(),v.getHeight(),true));
                v2.setImageBitmap(Bitmap.createScaledBitmap(bitmap , (int) (bitmap.getWidth()*(v2.getHeight()/bitmap.getHeight())*1.15),v2.getHeight(),true));
                break;
            case "1":
                v = root.findViewById(R.id.img1);
                v.setImageBitmap(bitmap);
                break;
            case "2":
                v = root.findViewById(R.id.img2);
                v.setImageBitmap(bitmap);
                break;
            case "3":
                v = root.findViewById(R.id.img3);
                v.setImageBitmap(bitmap);
                break;
            case "4":
                v = root.findViewById(R.id.img4);
                v.setImageBitmap(bitmap);
                break;
        }

//        Log.d("Localpath",localPath);
//        v.setImageURI(Uri.parse(localPath));

        return;
//        Bitmap img;
//        try {
//            File file = new File(savePath + "/" + fileName + "_s.jpg");
//            FileOutputStream out=new FileOutputStream(file);
//
//
//            img = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(localPath) ,100,100,true);
//            img.compress(Bitmap.CompressFormat.JPEG,100,out);
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        }

//        출처: http://lueseypid.tistory.com/15 [감성 개발자!]



//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
//        Bitmap src = BitmapFactory.decodeFile(savePath + "/" + fileName + ".jpg", options);
//        출처: http://it77.tistory.com/99 [시원한물냉의 사람사는 이야기]

//        //저장한 이미지 열기
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        String targetDir = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
//        File file = new File(targetDir + "/" + fileName + ".jpg");
//        //type 지정 (이미지)
//        i.setDataAndType(Uri.fromFile(file), "image/*");
//        getApplicationContext().startActivity(i);
//        //이미지 스캔해서 갤러리 업데이트
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }


