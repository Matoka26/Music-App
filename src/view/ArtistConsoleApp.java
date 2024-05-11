package view;

import models.Episode;
import models.Song;
import models.app_user.Artist;
import models.track.Album;
import models.track.Podcast;
import models.track.Track;
import persistence.repositories.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class ArtistConsoleApp {
    private static ArtistConsoleApp instance = null;
    private ArtistRepository artistRepo;
    private ListenerRepository listenerRepo;
    private PodcastRepository podcastRepo;
    private AlbumRepository albumRepo;
    private SongRepository songRepo;
    private EpisodeRepository episodeRepo;
    private TrackRepository trackRepo;

    private static Artist artist;
    private static int artist_id;


    private ArtistConsoleApp() {
        artistRepo = ArtistRepository.getInstance();
        listenerRepo = ListenerRepository.getInstance();
        podcastRepo = PodcastRepository.getInstance();
        albumRepo = AlbumRepository.getInstance();
        songRepo = SongRepository.getInstance();
        episodeRepo = EpisodeRepository.getInstance();
        trackRepo = TrackRepository.getInstance();
    }

    public void setArtist_id(int artist_id) {
        ArtistConsoleApp.artist_id = artist_id;
        try {
            this.artist = artistRepo.get(artist_id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArtistConsoleApp getInstance() {
        if(instance == null){
            instance = new ArtistConsoleApp();
        }
        return instance;
    }

    public void createArtist() {
        try {
            artistRepo.add(Artist.createArtist());
        }
        catch (Exception e) {
            System.out.println("Something went wrong, please try other values");
            createArtist();
        }
    }

    public Map.Entry<Integer,Integer> validateLogin(String username, String password){
        return artistRepo.validateLogin(username, password);
    }

    private void updateArtist(){
        this.artist.updateArtist();
        artistRepo.update(artist);
    }

    private void deleteArtist(){
        Scanner sc = new Scanner(System.in);
        this.artist.deleteArtist();
        artistRepo.update(artist);

        System.out.println("Your account has been deleted successfully");
        System.out.println("The app will now close, please press enter");
        sc.nextLine();
    }

    private void deleteTrack(int trackId){
        trackRepo.delete(trackId);
        System.out.println("The track has been deleted successfully");
        artistTrackMenu();
    }

    private void addSong(Album album){
        songRepo.add(Song.createSong(album));
        System.out.println("The song has been added successfully");
        artistTrackMenu();
    }
    private void addEpisode(Podcast podcast){
        episodeRepo.add(Episode.createEpidose(podcast));
        System.out.println("The episode has been added successfully");
        artistTrackMenu();
    }

    private void addTrack(){
        Scanner sc = new Scanner(System.in);

        System.out.println("What kind of track?\n" +
                            "1.Album\n" +
                            "2.Podcast\n" +
                            "3.Exit\n");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option){
            case 1 -> addAlbum();
            case 2 -> addPodcast();
            case 3 -> startMenu();
        }
    }

    private void addAlbum(){
        albumRepo.add(Album.createAlbum(this.artist));
        System.out.println("Album created successfully");
        startMenu();
    }
    private void addPodcast(){
        podcastRepo.add(Podcast.createPodcast(this.artist));
        System.out.println("Podcast created successfully");
        startMenu();
    }

    private void showTrackDetail(int trackId){
        Track track = albumRepo.getByTrackId(trackId);
        if(track != null){
            System.out.println("ALBUM:");
            System.out.println(track);

            Album album = (Album) track;
            ArrayList<Song> songs = songRepo.getAllByAlbumId(album.getAlbum_id());
            Collections.sort(songs);

            for(Song song : songs){
                System.out.println(song);
            }

            System.out.println("1.Add to it\n" +
                                "2.Exit\n" +
                                "3.End");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch(choice){
                case 1 -> addSong(album);
                case 2 -> artistTrackMenu();
                case 3 -> {}
            }

            return;
        }
        track = podcastRepo.getByTrackId(trackId);

        if(track != null){
            System.out.println("PODCAST:");
            System.out.println(track);

            Podcast podcast = (Podcast) track;
            ArrayList<Episode> episodes = episodeRepo.getAllbyPodcastId(podcast.getPodcast_id());
            Collections.sort(episodes);

            for(Episode episode : episodes){
                System.out.println(episode);
            }

            System.out.println("1.Add to it\n" +
                    "2.Exit\n" +
                    "3.End");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch(choice){
                case 1 -> addEpisode(podcast);
                case 2 -> artistTrackMenu();
                case 3 -> {}
            }
            return;
        }

    }

    private void artistProfileMenu(){
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println(this.artist);
        System.out.println("\n1.Update something\n" +
                            "2.Delete account\n" +
                            "3.Exit\n" +
                            "4.End\n");

        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> {
                updateArtist();
                artistProfileMenu();
            }
            case 2 -> deleteArtist();
            case 3 -> startMenu();
            case 4 -> {}
        }
    }

    private void changeTrack(){
        Scanner sc = new Scanner(System.in);
        int option, trackId;

        System.out.println("Please enter the id of the track that you wanna update: ");
        trackId = sc.nextInt();
        sc.nextLine();

        System.out.println("What action would you like to perform\n" +
                            "1.See in more detail\n" +
                            "2.Delete\n" +
                            "3.Nevermind\n" +
                            "4.End\n");
        option = sc.nextInt();
        sc.nextLine();
        switch (option){
            case 1 -> showTrackDetail(trackId);
            case 2 -> deleteTrack(trackId);
            case 3 -> artistTrackMenu();
            case 4 -> {}
        }
    }

    private void artistTrackMenu(){
        Scanner sc = new Scanner(System.in);
        int option;
        ArrayList<Track> tracks = trackRepo.getAllByArtistId(this.artist);

        System.out.println(artist.getUsername() + "'s tracks:");
        for(Track track : tracks){
            System.out.println(track);
        }

        System.out.println("\n\n1.Change something\n" +
                            "2.Exit\n" +
                            "3.End\n");
        option = sc.nextInt();
        sc.nextLine();
        switch (option){
            case 1 -> changeTrack();
            case 2 -> startMenu();
            case 3 -> {}
        }
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("\n\n\nWelcome  " + artist.getUsername() + "!!!");
        System.out.println("Choose your next action:\n" +
                "1.See your Tracks\n" +
                "2.Add a new Track\n" +
                "3.See your profile\n" +
                "4.End");

        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> artistTrackMenu();
            case 2 -> addTrack();
            case 3 -> artistProfileMenu();
            case 4 -> {}
        }
    }


}
