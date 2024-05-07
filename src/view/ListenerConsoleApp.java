package view;

import models.app_user.Listener;
import persistence.repositories.*;

import java.util.Map;

public class ListenerConsoleApp {
    private static ListenerConsoleApp instance = null;
    private ArtistRepository artistRepo;
    private ListenerRepository listenerRepo;
    private PodcastRepository podcastRepo;
    private AlbumRepository albumRepo;
    private SongRepository songRepo;
    private EpisodeRepository episodeRepo;

    private static int user_id;
    private static int listener_id;

    private ListenerConsoleApp() {
        artistRepo = ArtistRepository.getInstance();
        listenerRepo = ListenerRepository.getInstance();
        podcastRepo = PodcastRepository.getInstance();
        albumRepo = AlbumRepository.getInstance();
        songRepo = SongRepository.getInstance();
        episodeRepo = EpisodeRepository.getInstance();
    }

    public static ListenerConsoleApp getInstance() {
        if (instance == null) {
            instance = new ListenerConsoleApp();
        }
        return instance;
    }

    public void createListener(){
        listenerRepo.add(Listener.createListener());
    }

    public Map.Entry<Integer,Integer> validateLogin(String username, String password){
        return listenerRepo.validateLogin(username, password);
    }

    public void startMenu(int user_id, int listener_id){
        this.user_id = user_id;
        this.listener_id = listener_id;

    }
}
