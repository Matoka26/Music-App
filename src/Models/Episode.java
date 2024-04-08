package Models;

import Models.Track.Podcast;

public class Episode {
    private int episode_id;
    private Podcast podcast;
    private String title;
    private String host;
    private String guest;
    private int duration;
    private int likes;
    private int plays;

    public Episode(int episode_id, Podcast podcast, String title, String host, String guest, int duration, int likes, int plays) {
        this.episode_id = episode_id;
        this.podcast = podcast;
        this.title = title;
        this.host = host;
        this.guest = guest;
        this.duration = duration;
        this.likes = likes;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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
                '\u2764' + likes + '\n' +
                 '\u25B6' + plays + '\n';
    }
}
