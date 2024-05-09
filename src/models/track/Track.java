package models.track;

import models.app_user.Artist;

import java.sql.Date;

public class Track {
    private int track_id;
    private Artist artist;
    private String name;
    private String picture;
    private Date release_date;

    public Track(int track_id, Artist artist, String name, String picture, Date release_date) {
        this.track_id = track_id;
        this.artist = artist;
        this.name = name;
        this.picture = picture;
        this.release_date = release_date;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString(){
        return picture + '\n' +
                name + '\n' +
                "By " + artist.getUsername() + '\n' +
                release_date + '\n' +
                "ID " + track_id + '\n';
    }
}
