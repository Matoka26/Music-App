package models.track;

import models.app_user.Artist;
import models.Episode;

import java.sql.Date;
import java.util.ArrayList;

public class Podcast extends Track{
    private int podcast_id;
    private String topic;
    private String description;
    private ArrayList<Episode> episodes;

    public Podcast(int track_id, Artist artist, String name,
                   String picture, Date release_date, int podcast_id,
                   String topic, String description, ArrayList<Episode> episodes) {
        super(track_id, artist, name, picture, release_date);
        this.podcast_id = podcast_id;
        this.topic = topic;
        this.description = description;
        this.episodes = episodes;
    }

    public int getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(int podcast_id) {
        this.podcast_id = podcast_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public String toString(){
        return super.toString() +
                "Topic: " + topic + '\n' +
                "Description:\n\t " + description + '\n';
    }
}
