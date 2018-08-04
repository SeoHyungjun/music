package com.example.hyungjun.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.ceau.itunesapi.Search;
import be.ceau.itunesapi.request.Country;
import be.ceau.itunesapi.request.Entity;
import be.ceau.itunesapi.request.search.Media;
import be.ceau.itunesapi.response.Response;
import be.ceau.itunesapi.response.Result;

public class Itunes_Search extends AsyncTask<String,String,String> {
    MediaPlayer mediaPlayer;
//    TextView l1_music, l2_music, l3_music, l4_music;
//    TextView l1_singer, l2_singer, l3_singer, l4_singer;
    DataManager dataManager = DataManager.getInstance();

    Itunes_Search()
    {
        mediaPlayer = new MediaPlayer();
    }

    public void search()
    {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(edit_title.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(edit_artist.getWindowToken(), 0);
//        textView.setText("searching....");
//        imageView.setImageBitmap(null);
//        a_search = new A_search();
//        a_search.execute();
    }
    public void search(View v)
    {
//        ituens_search();
//        new ServerConn(textView).execute("1","데리러가");



//        출처: http://itpangpang.xyz/304 [ITPangPang]

    }

    public void play(String musicURL)
    {
//        new ServerConn(textView).execute(editText.getText().toString(),"2","데리러가");
//        new ServerConn(textView).execute("1","데리러가");
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(edit_title.getWindowToken(), 0);
//        imm.hideSoftInputFromWindow(edit_artist.getWindowToken(), 0);
        mediaPlayer.pause();
        mediaPlayer.stop();
//        mediaPlayer.release();
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.stop();
        mediaPlayer.reset();
        if(!musicURL.equals("")) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(musicURL);
//                mediaPlayer.prepareAsync();
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
        }


//                    출처: http://unikys.tistory.com/350 [All-round programmer]
    }
        @Override
        protected String doInBackground(String... strings) {
            List<Result> results = null;


            try {
                Response response = new Search(strings[0] +"+"+strings[1])
                        .setCountry(Country.UNITED_STATES)
//                        .setCountry(Country.SOUTH_KOREA)
//                        .setAttribute(Attribute.SONG_TERM)
                        //.setAttribute(Attribute.ARTIST_TERM)

//                        .setAttribute(Attribute.SONG_TERM)
//                        .setAttribute(Attribute.KEYWORDS_TERM)
                        .setMedia(Media.MUSIC)
                        .setEntity(Entity.SONG)
                        .setLimit(1)
                        .execute();

                Log.d("d_resp", response.toString());
                results = response.getResults();
                if (results != null && results.size() > 0) {
                    for(Result result : results) {
                        Log.d("prevURL",result.getPreviewUrl());
//                        play(result.getPreviewUrl());
//                        imageDownload.execute(result.getArtworkUrl100(), "cache", (result.getTrackName()+"_"+result.getArtistName()));
//                        new ImageDownload().execute(result.getArtworkUrl100(), "cache", (result.getTrackName()+"_"+result.getArtistName()));
//                        while(img_result.equals("fin")){Log.d("while","~~~~~~~~~~~~~~~~~~");}

//                        imageDownload.wait(1000);

                    }
                } else {
                }


            }catch (Exception e)
            {
                Log.e("error",e.toString());
            }
//            return new ArrayList<String>(Arrays.asList(strings[0],strings[1],strings[2]));

            return results.get(0).getPreviewUrl();
        }

        @Override
        protected void onPostExecute(final String preview_URL) {
            super.onPostExecute(preview_URL);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    play(preview_URL);
                }
            }).start();

//
//            switch (music_data.get(0)) {
//                case "1":
//                    //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//                    //img1.setImageDrawable(bitmap);
//                    l1_music.setText(sing);
//                    l1_singer.setText(singer);
//                    break;
//                case "2":
//                    //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
//                    //img2.setImageDrawable(bitmap);
//                    l2_music.setText(sing);
//                    l2_singer.setText(singer);
//                    break;
//                case "3":
//                    l3_music.setText(sing);
//                    l3_singer.setText(singer);
//                    break;
//
//                case "4":
//                    l4_music.setText(sing);
//                    l4_singer.setText(singer);
//                    break;
//            }
////
////            if (music_data[0] == 1) {
////
////            }
////            else if (num == 2) {
////
////            }
////            else if (num == 3) {
////                //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
////                //img2.setImageDrawable(bitmap);
////                l2_music.setText(sing);
////                l2_singer.setText(singer);
////            }
////            else if (num == 4) {
////                //bitmap = (BitmapDrawable) res.getDrawable(R.drawable.i1);
////                //img2.setImageDrawable(bitmap);
////                l2_music.setText(sing);
////                l2_singer.setText(singer);
////            }
////            else
////                return;
////
////
//            textView.setText("");
//            if (results != null && results.size() > 0) {
//                for(Result result : results) {
//                    textView.append("제목: "+ result.getTrackName()+"\n");
//                    textView.append("가수: "+result.getArtistName()+"\n");
//                    textView.append("앨범: "+result.getCollectionName()+"\n\n");
//
//                    Log.d("search","제목: "+ result.getTrackName()+"\n");
//                    Log.d("search","가수: "+result.getArtistName()+"\n");
//                    Log.d("search","앨범: "+result.getCollectionName()+"\n\n");
////                    textView.append(result.getPreviewUrl()+"\n");
////                    textView.append(result.getArtworkUrl100()+"\n");
////                    imageView.setImageURI(Uri.parse(getCacheDir()+"/"+result.getTrackName()+"_"+result.getArtistName()+".jpg"));
//                    play(result.getPreviewUrl());
////                    musicURL = result.getPreviewUrl();
//
//
//                    Log.d("fileURI",getCacheDir()+"/"+result.getTrackName()+"_"+result.getArtistName()+".jpg");
//                    Log.d("prevURL",result.getTrackName());
//                    Log.d("prevURL",result.getArtistName());
//                    Log.d("prevURL",result.getPreviewUrl());
//                    Log.d("prevURL",result.getArtworkUrl100()); // 앨범사진 // 512면 없으면 안받아 지는듯
//                    Log.d("prevURL",result.getTrackViewUrl()); // 애플 뮤직에 연결
//                    System.out.println(result.getArtistName().toString());
//                }
//                return;
//
//            } else {
//                println("No results found :(");
//                return;
////                System.out.println("No results found :(");
//            }

        }
        protected void onCancelled()
        {
            super.onCancelled();
            Log.d("async","stop!! async");

        }

    public void println(String data) {
        Toast.makeText(dataManager.getActivity(), data, Toast.LENGTH_LONG).show();
    }


}
