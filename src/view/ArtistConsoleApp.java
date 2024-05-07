package view;

import models.app_user.Artist;
import models.track.Track;
import persistence.repositories.*;

import java.util.ArrayList;
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

    private static Artist artist;
    private static int artist_id;


    private ArtistConsoleApp() {
        artistRepo = ArtistRepository.getInstance();
        listenerRepo = ListenerRepository.getInstance();
        podcastRepo = PodcastRepository.getInstance();
        albumRepo = AlbumRepository.getInstance();
        songRepo = SongRepository.getInstance();
        episodeRepo = EpisodeRepository.getInstance();
    }

    public void setArtist_id(int artist_id) {
        ArtistConsoleApp.artist_id = artist_id;
        this.artist = artistRepo.get(artist_id);
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

    private void updateArtist(){
        this.artist.updateArtist();
        artistRepo.update(artist);
    }
    private void deleteArtist(){
        Scanner sc = new Scanner(System.in);
        this.artist.deleteArtist();
        System.out.println("Your account has been deleted successfully");
        System.out.println("The app will now close, please press enter");
        sc.nextLine();
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

        // sa verifici intai daca e si din lista ta
        System.out.println("Please enter the id of the track that you wanna update: ");
        trackId = sc.nextInt();
        sc.nextLine();

        System.out.println("What action would you like to perform\n" +
                            "1.See in more detail\n" +
                            "2.Delete\n" +
                            "3.Nevermind\n");
        option = sc.nextInt();
        sc.nextLine();
        switch (option){
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
        }
    }

    private void artistTrackMenu(){
        Scanner sc = new Scanner(System.in);
        int option;
        ArrayList<Track> tracks = artistRepo.getAllArtistTracks(this.artist);
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
                "4.Exit");

        option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> artistTrackMenu();
            case 2 -> {}
            case 3 -> artistProfileMenu();
            case 4 -> {}
        }
    }


}
