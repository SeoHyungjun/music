package com.example.hyungjun.music;

import java.lang.ref.SoftReference;

/**
 * Created by MIN on 2018-06-09.
 */

public class Music_Data {



    public String title;
    public String singer;
    public String album;
    public String itunes_artwork_url;

    public int music_id;
    public String user_id;
    public String mood;

//    int ranking;


    Music_Data(int music_id, String title, String singer, String album, String itunes_artwork_url)
    {
        this.music_id =music_id;
      this.title = title;
      this.singer = singer;
      this.album = album;
      this.itunes_artwork_url = itunes_artwork_url;
    }

    Music_Data(String user_id ,int music_id, String mood)
    {
        this.user_id = user_id;
        this.music_id = music_id;
        this.mood = mood;
    }


}
