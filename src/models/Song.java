package models;

import models.track.Album;

import java.util.Scanner;

public class Song implements Comparable<Song>{
    private int song_id;
    private Album album;
    private String title;
    private int duration;
    private int plays;

    public Song(int song_id, Album album, String title,
                int duration, int plays) {
        this.song_id = song_id;
        this.album = album;
        this.title = title;
        this.duration = duration;
        this.plays = plays;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    @Override
    public String toString(){
        return album.getPicture() + '\n' +
                title + '\n' +
                album.getArtist().getUsername() + '\n' +
                // for feat
                "Duration: " + duration + '\n' +
                '\u25B6' + plays + '\n' +
                "ID " + song_id + '\n';
    }

    @Override
    public int compareTo(Song o) {
        return Integer.compare(this.plays, o.plays);
    }

    public static Song createSong(Album album){
        Scanner sc = new Scanner(System.in);
        System.out.println("Song title: ");
        String title = sc.nextLine();

        System.out.println("Song duration: ");
        int duration = sc.nextInt();
        sc.nextLine();

        return new Song(0, album, title, duration, 0);
    }

    public void onePlay(){
        this.plays += 1;
    }
}
