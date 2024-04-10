import Models.APP_User.Artist;
import Models.APP_User.Listener;
import Models.Episode;
import Models.Song;
import Models.Track.Album;
import Models.Track.Podcast;
import Persistence.Repositories.*;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;

public abstract class Main {
    public static void main(String[] args) {
        ArtistRepository  artistRepo = ArtistRepository.getInstance();
        ListenerRepository listenerRepo = ListenerRepository.getInstance();
        PodcastRepository podcastRepo = PodcastRepository.getInstance();
        AlbumRepository albumRepo = AlbumRepository.getInstance();
        SongRepository songRepo = SongRepository.getInstance();
        EpisodeRepository episodeRepo = EpisodeRepository.getInstance();

        Date date = Date.valueOf("2003-09-24");



        Artist anotherUser = new Artist(101, "John", "Doe",
                "john.doe@example.com", "johndoe123", "profile_pic_url", date, "7193211",false,
                150, "Great artist", 55000, false, "Record Label X", new ArrayList<>());

        Listener user2 = new Listener(101, "John", "Doe",
                "john.doe@example.com", "johndoe123", "profile_pic_url", date, "7193211",false,
                150,0 );

        Podcast pod = new Podcast(2, artistRepo.get(2) , "Nu stiu, nu cunosc",
                "poza/random", date, 20, "testare", "care e diferenta dintre un scaun si un magar?", new ArrayList<>());
        Album al = albumRepo.get(1);
        Song song = new Song(1,al, "Cyberfvker", 120,0,0);

        Episode episode = new Episode(1,pod,"NU MAI DUC", "Barack Obama", "Kim Jong-Un", 181,10,100);


        ArrayList<Episode> ep = episodeRepo.getAll();
;
        Episode e = ep.get(0);
        System.out.println(e);

        e.setTitle("Ce spun romanii");

        episodeRepo.update(e);
        System.out.println(e);
        episodeRepo.delete(e);
    }
}