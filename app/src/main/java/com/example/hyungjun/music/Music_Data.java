package com.example.hyungjun.music;

/**
 * Created by MIN on 2018-06-09.
 */

public class Music_Data {



//    int artist_id;
    public String title;
    public String singer;
    public String album;
    public String itunes_artwork_url;
//    int ranking;


    Music_Data(String title, String singer, String album, String itunes_artwork_url)
    {
      this.title = title;
      this.singer = singer;
      this.album = album;
      this.itunes_artwork_url = itunes_artwork_url;
    }




}
