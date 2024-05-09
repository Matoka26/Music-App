package view;

import models.Episode;
import models.Song;
import models.app_user.Artist;
import models.app_user.Listener;
import models.track.Album;
import models.track.Podcast;
import models.track.Track;
import persistence.repositories.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ListenerConsoleApp {
    private static ListenerConsoleApp instance = null;
    private ArtistRepository artistRepo;
    private ListenerRepository listenerRepo;
    private PodcastRepository podcastRepo;
    private AlbumRepository albumRepo;
    private SongRepository songRepo;
    private EpisodeRepository episodeRepo;
    private TrackRepository trackRepo;

    private static Listener listener;
    private static int listener_id;

    private static Queue<Song> songQueue = new LinkedList<>();


    private ListenerConsoleApp() {
        artistRepo = ArtistRepository.getInstance();
        listenerRepo = ListenerRepository.getInstance();
        podcastRepo = PodcastRepository.getInstance();
        albumRepo = AlbumRepository.getInstance();
        songRepo = SongRepository.getInstance();
        episodeRepo = EpisodeRepository.getInstance();
        trackRepo = TrackRepository.getInstance();
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

    public void setListener_id(int artist_id) {
        ListenerConsoleApp.listener_id = artist_id;
        this.listener = listenerRepo.get(artist_id);
    }

    private void displayDots(){
        try{
            for(int i = 0 ; i < 3 ; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        finally {
            System.out.println();
        }
    }

    private void addDuration(Song song){
        this.listener.addDuration(song.getDuration());
        song.onePlay();

        listenerRepo.update(this.listener);
        songRepo.update(song);
    }

    private void addDuration(Episode episode){
        this.listener.addDuration(episode.getDuration());
        episode.onePlay();

        listenerRepo.update(this.listener);
        episodeRepo.update(episode);
    }

    private void likeSong(Song song){
        try {
            listenerRepo.likeSong(this.listener.getListener_id(), song.getSong_id());
        }catch (Exception e){
            System.out.println("Oops you've already liked this one\n");
            System.out.println("Press yes if you want to unlike it: ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("yes")){
                listenerRepo.unlikeSong(this.listener.getListener_id(), song.getSong_id());
            }
        }
        finally {
            startMenu();
        }
    }

    private void showTrackDetail(int trackId) {
        Scanner sc = new Scanner(System.in);
        Track track = albumRepo.getByTrackId(trackId);
        if (track != null) {
            System.out.println("ALBUM:");
            System.out.println(track);

            Album album = (Album) track;
            ArrayList<Song> songs = songRepo.getAllByAlbumId(album.getAlbum_id());
            Collections.sort(songs);

            int i = 1;
            for (Song song : songs) {
                System.out.println(i++);
                System.out.println(song);
            }

            System.out.println("\nWanna listen to one of them?\n" +
                    "1.Yeah sure\n" +
                    "2.Nah,exit\n" +
                    "3.End\n");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1 -> chooseSong(songs);
                case 2 -> startMenu();
                case 3 -> {
                }

            }

            track = podcastRepo.getByTrackId(trackId);
            if (track != null) {
                System.out.println("PODCAST:");
                System.out.println(track);

                Podcast podcast = (Podcast) track;
                ArrayList<Episode> episodes = episodeRepo.getAllbyPodcastId(podcast.getPodcast_id());
                Collections.sort(episodes);

                for (Episode episode : episodes) {
                    System.out.println(episode);
                }
            }
            return;
        }
    }

    private void showTracks(Artist artist){
        Scanner scanner = new Scanner(System.in);

        ArrayList<Track> tracks = trackRepo.getAllByArtistId(artist);

        if(tracks.isEmpty()){
            System.out.println(artist.getUsername() + "has no tracks yet,sorry\n");
            startMenu();
            return;
        }

        System.out.println(artist.getUsername() + "'s tracks:");
        for(Track track : tracks){
            System.out.println(track);
        }

        System.out.println("1.See one in more detail\n" +
                            "2.Exit\n" +
                            "3.End\n");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> {
                System.out.println("Please enter the track ID: ");
                int trackId = scanner.nextInt();
                scanner.nextLine();
                showTrackDetail(trackId);
            }
            case 2 -> startMenu();
            case 3 -> {}
        }

    }
    private void showLikes(){
        ArrayList<Song> likedSongs = songRepo.getLikes(listener_id);
        for(Song song : likedSongs){
            System.out.println(song);
        }
        startMenu();
    }

    private void listenSong(Song song){
        System.out.println("Now playing " + song.getTitle());
        displayDots();

        addDuration(song);
    }

    private void chooseSong(ArrayList<Song> randSongs){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the index of the desired song: ");
        int song_index = scanner.nextInt() - 1;
        scanner.nextLine();

        listenSong(randSongs.get(song_index));

        System.out.println("\n\nWhat a wonderful song! Would you like to:\n" +
                            "1.Like it\n" +
                            "2.See album\n" +
                            "3.Add to queue\n" +
                            "4.Or not\n");

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> {
                likeSong(randSongs.get(song_index));
                startMenu();
            }
            case 2 -> chooseSong(songRepo.getAllByAlbumId(randSongs.get(song_index).getAlbum().getAlbum_id()));
            case 3 -> {
                songQueue.add(randSongs.get(song_index));
                startMenu();
            }
            case 4 -> startMenu();
        }
    }

    private void chooseEpisode(ArrayList<Episode> episodes){
        if(episodes.isEmpty()){
            System.out.println("The podcast has no episodes yet,sorry\n");
            startMenu();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEPISODES:\n");
        int i = 1;
        for(Episode episode : episodes){
            System.out.println(i++);
            System.out.println(episode);
        }

        System.out.println("Please enter the index of the desired episode: ");
        int episodeIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        boolean loop = false;

        while(!loop){
            if(episodeIndex >= episodes.size()){
                loop = true;
                System.out.println("This was the last episode of the podcast :(\n" +
                        "1.Exit\n" +
                        "2.End\n");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1 -> startMenu();
                    case 2 -> {}
                }
                return;
            }

            System.out.println("Now playing " + episodes.get(episodeIndex).getTitle());
            displayDots();
            addDuration(episodes.get(episodeIndex));
            System.out.println("The episode " + episodes.get(episodeIndex).getTitle() + " has been played\n" +
                                "1.Next episode\n" +
                                "2.End\n" +
                                "3.Exit\n" );

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1 -> {episodeIndex += 1;}
                case 2 -> {loop = true;}
                case 3 -> {
                    startMenu();
                    return;
                }
            }
        }
    }

    private void choosePodcast(ArrayList<Podcast> podcasts){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the index of the desired podcast: ");
        int podcastIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        ArrayList<Episode> episodes = episodeRepo.getAllbyPodcastId(podcasts.get(podcastIndex).getPodcast_id());

        chooseEpisode(episodes);
    }

    private void searchArtistMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for: ");
        String search = scanner.nextLine();

        Artist searchArtist = artistRepo.getByUsername(search);

        if(searchArtist == null){
            System.out.println("Sorry, that search doesn't exist");
            startMenu();
            return;
        }

        System.out.println(searchArtist);
        System.out.println("\n1.See tracks\n" +
                            "2.Exit\n" +
                            "3.End\n");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> showTracks(searchArtist);
            case 2 -> startMenu();
            case 3 -> {}
        }
    }

    private void searchSongMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for: ");
        String search = scanner.nextLine();

        ArrayList<Song> searchSongs = songRepo.getByTitle(search);

        if(searchSongs.isEmpty()){
            System.out.println("Sorry, that search doesn't exist");
            startMenu();
            return;
        }

        int i = 1;
        for (Song song : searchSongs) {
            System.out.println(i++);
            System.out.println(song);
        }

        System.out.println("\nIs one of them?\n" +
                "1.Yeah\n" +
                "2.Nah, lemme search again\n" +
                "3.Exit\n" +
                "4.End\n");

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> chooseSong(searchSongs);
            case 2 -> searchSongMenu();
            case 3 -> startMenu();
            case 4 -> {}
        }
    }

    private void searchPodcastMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for: ");
        String search = scanner.nextLine();

        ArrayList<Podcast> searchPodcast = podcastRepo.getByTitle(search);
        if(searchPodcast.isEmpty()){
            System.out.println("Sorry, that search doesn't exist");
            startMenu();
            return;
        }
        int i = 1;
        for (Podcast podcast : searchPodcast) {
            System.out.println(i++);
            System.out.println(podcast);
        }

        System.out.println("\nIs one of them?\n" +
                "1.Yeah\n" +
                "2.Nah, lemme search again\n" +
                "3.Exit\n" +
                "4.End\n");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> choosePodcast(searchPodcast);
            case 2 -> searchPodcastMenu();
            case 3 -> startMenu();
            case 4 -> {}
        }
    }

    private void randomSongsMenu(){
        Scanner sc = new Scanner(System.in);
        int option;

        ArrayList<Song> randSongs = songRepo.getFewRandom();

        int i = 1;
        for (Song song : randSongs) {
            System.out.println(i++);
            System.out.println(song);
        }

        System.out.println("\nWanna listen to one of them?\n" +
                            "1.Yeah sure\n" +
                            "2.Nah, gimme other songs\n" +
                            "3.Exit\n" +
                            "4.End\n");
        option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> chooseSong(randSongs);
            case 2 -> randomSongsMenu();
            case 3 -> startMenu();
            case 4 -> {}

        }
    }

    private void randomPodcastMenu(){
        Scanner sc = new Scanner(System.in);
        int option;

        ArrayList<Podcast> randPodcasts = podcastRepo.getFewRandom();
        int i = 1;
        for (Podcast podcast : randPodcasts) {
            System.out.println(i++);
            System.out.println(podcast);
        }

        System.out.println("\nWanna listen to one of them?\n" +
                "1.Yeah sure\n" +
                "2.Nah, gimme other podcasts\n" +
                "3.Exit\n" +
                "4.End\n");
        option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> choosePodcast(randPodcasts);
            case 2 -> randomPodcastMenu();
            case 3 -> startMenu();
            case 4 -> {}
        }
    }

    private void listenQueue(){
        if(songQueue.isEmpty())
            System.out.println("Sorry, you must first add songs");

        while(!songQueue.isEmpty()){
            listenSong(songQueue.poll());
        }
    }

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("\n\n\nWelcome  " + listener.getUsername() + "!!!");
        System.out.println("Choose your next action:\n" +
                "1.Show a few random songs\n" +
                "2.Search for a song\n" +
                "3.Search an artist\n" +
                "4.Show a few podcasts\n" +
                "5.Search a podcast\n" +
                "6.Listen to your queue\n" +
                "7.Show my likes\n" +
                "8.End\n");

        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1 -> randomSongsMenu();
            case 2 -> searchSongMenu();
            case 3 -> searchArtistMenu();
            case 4 -> randomPodcastMenu();
            case 5 -> searchPodcastMenu();
            case 6 -> listenQueue();
            case 7 -> showLikes();
            case 8 -> {}
        }
    }
}
