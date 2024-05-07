package models.app_user;

import models.track.Track;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Artist extends App_User{
    private int artist_id;
    private String description;
    private int monthly_listeners;
    private Boolean verified;
    private String label;
    private ArrayList<Track> tracks;

    public Artist(int user_id, String first_name, String last_name,
                  String email, String username, String password, String profile_pic,
                  Date register_date, String phone_number,Boolean deleted ,int artist_id,
                  String description, int monthly_listeners, Boolean verified,
                  String label, ArrayList<Track> tracks) {
        super(user_id, first_name, last_name, email, username,password, profile_pic, register_date, phone_number, deleted);
        this.artist_id = artist_id;
        this.description = description;
        this.monthly_listeners = monthly_listeners;
        this.verified = verified;
        this.label = label;
        this.tracks = tracks;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public String getDescription() {
        return description;
    }

    public int getMonthly_listeners() {
        return monthly_listeners;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getLabel() {
        return label;
    }
    public ArrayList<Track> getTracks(){ return tracks; }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMonthly_listeners(int monthly_listeners) {
        this.monthly_listeners = monthly_listeners;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTracks(ArrayList<Track> tracks){ this.tracks = tracks; }

    @Override
    public String toString(){
        Character verif = (verified) ? '\u2713' : '\u0068';
        return username + " (" + artist_id + ") " + + verif + '\n' +
                "----------------\n" +
                "Description:\n\t" + description + '\n' +
                "Label: " + label;
    }

    public static Artist createArtist(){
        Scanner sc = new Scanner(System.in);

        String first_name, last_name, email, username, password, profile_pic, phone_number;
        Date register_date;
        String description, label;

        System.out.println("First name: ");
        first_name = sc.nextLine();
        System.out.println("Last name: ");
        last_name = sc.nextLine();
        System.out.println("Email: ");
        email = sc.nextLine();
        System.out.println("Username: ");
        username = sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine();
        System.out.println("Profile picture: ");
        profile_pic = sc.nextLine();
        System.out.println("Phone number: ");
        phone_number = sc.nextLine();
        register_date = new Date(System.currentTimeMillis());
        System.out.println("Label: ");
        label = sc.nextLine();
        System.out.println("Description: ");
        description = sc.nextLine();

        return new Artist(0,first_name, last_name, email, username, password,
                        profile_pic, register_date, phone_number, false,0,
                        description, 0, false, label, new ArrayList<>());
    }
}
