package models;

import models.track.Podcast;

import java.util.Scanner;

public class Episode implements Comparable<Episode> {
    private int episode_id;
    private Podcast podcast;
    private String title;
    private String host;
    private String guest;
    private int duration;
    private int plays;

    public Episode(int episode_id, Podcast podcast, String title, String host, String guest, int duration, int plays) {
        this.episode_id = episode_id;
        this.podcast = podcast;
        this.title = title;
        this.host = host;
        this.guest = guest;
        this.duration = duration;
        this.plays = plays;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(int episode_id) {
        this.episode_id = episode_id;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
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
    public String toString() {
        String gue = (guest != null) ? "Guest: " + guest + '\n' : "";
        return title + '\n' +
                "Host: " + host + '\n' +
                gue +
                "Duration: " + duration + '\n' +
                 '\u25B6' + plays + '\n';
    }

    @Override
    public int compareTo(Episode o) {
        return Integer.compare(this.plays, o.getPlays());
    }

    public static Episode createEpidose(Podcast podcast){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Episode title: ");
        String title = scanner.nextLine();

        System.out.println("Episode host: ");
        String host = scanner.nextLine();

        System.out.println("Episode guest: ");
        String guest = scanner.nextLine();

        System.out.println("Episode duration: ");
        int duration = scanner.nextInt();
        return new Episode(0, podcast,title, host, guest, duration, 0);
    }

    public void onePlay(){
        this.plays += 1;
    }
}
