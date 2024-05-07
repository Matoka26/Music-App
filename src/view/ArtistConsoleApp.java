package view;

import models.app_user.Artist;
import persistence.repositories.*;

import java.io.Console;
import java.util.Map;

public class ArtistConsoleApp {
    private static ArtistConsoleApp instance = null;
    private ArtistRepository artistRepo;
    private ListenerRepository listenerRepo;
    private PodcastRepository podcastRepo;
    private AlbumRepository albumRepo;
    private SongRepository songRepo;
    private EpisodeRepository episodeRepo;


    private ArtistConsoleApp() {
        artistRepo = ArtistRepository.getInstance();
        listenerRepo = ListenerRepository.getInstance();
        podcastRepo = PodcastRepository.getInstance();
        albumRepo = AlbumRepository.getInstance();
        songRepo = SongRepository.getInstance();
        episodeRepo = EpisodeRepository.getInstance();
    }

    public static ArtistConsoleApp getInstance() {
        if(instance == null){
            instance = new ArtistConsoleApp();
        }
        return instance;
    }

    public void createArtist(){
        artistRepo.add(Artist.createArtist());
    }

    public Map.Entry<Integer,Integer> validateLogin(String username, String password){
        return artistRepo.validateLogin(username, password);
    }

    public void startMenu(int user_id, int artist_id){

    }


}
