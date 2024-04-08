import Models.APP_User.Artist;
import Models.APP_User.Listener;
import Models.Episode;
import Models.Song;
import Models.Track.Album;
import Models.Track.Podcast;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Main {
    public static void main(String[] args) {
        Date date = Date.valueOf("2003-09-24");

        Artist user = new Artist(69, "Mihai", "Mihai" ,
                "email@example.com", "Matoka26", "prof/pic", date, "099",
                420, "Cel mai tare trapper", 40000,true,"SeekMusic",new ArrayList<>());
//        System.out.println(user);

        Listener user2 = new Listener(69, "Mihai", "Mihai" ,
                "email@example.com", "Matoka26", "prof/pic", date, "099",
                10, 10);
//        System.out.println(user2);

        Podcast podcast = new Podcast(1234, user, "Sample Podcast", "sample_picture.jpg", date, 5678, "Technology", "This is a sample podcast description.",new ArrayList<>());

        //System.out.println(podcast);

        Episode episode = new Episode(
                1,                      // episode_id
                podcast,                // podcast
                "Episode Title",        // title
                "Host Name",            // host
                "Guest Name",           // guest
                60,                     // duration (in minutes)
                100,                    // likes
                5000                    // plays
        );

        //System.out.println(episode);

        Album myAlbum = new Album(
                1,                           // track_id
                user,      // artist
                "My Album",                  // name
                "album_cover.jpg",           // picture
                Date.valueOf("2023-01-01"), // release_date
                101,                         // album_id
                "Rock"                       // genre
        );
        //System.out.println(myAlbum);
        Song mySong = new Song(
                1,                        // song_id
                new Album(
                        101,                   // track_id
                        user,// artist
                        "My Album",            // name
                        "album_cover.jpg",     // picture
                        Date.valueOf("2023-01-01"), // release_date
                        201,                   // album_id
                        "Rock"                 // genre
                ),
                "My Song",                // title
                180,                      // duration (in seconds)
                1000,                     // plays
                500                       // likes
        );
        System.out.println(mySong);
    }
}